package net.sparkminds.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import lombok.RequiredArgsConstructor;
import net.sparkminds.service.ApplicationService;
import net.sparkminds.service.dto.request.ApplicationRequestDTO;
import net.sparkminds.service.dto.response.ApplicationResponseDTO;
import net.sparkminds.utils.PDFGenerator;

@RestController
@RequestMapping("api/applications")
@RequiredArgsConstructor
public class ApplicationController {

	private final ApplicationService applicationService;
	private final RedisTemplate<String, Object> template;

	@GetMapping
	public ResponseEntity<List<ApplicationResponseDTO>> getAllApplication() {
//		String token = headers.getFirst(HttpHeaders.AUTHORIZATION);@RequestHeader HttpHeaders headers
//		boolean check = template.opsForValue().get(token.split(" ")[1]) == null;
//		if (!check) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		} else {
			return ResponseEntity.ok(applicationService.getAllApplication());
//		}
	}

	@GetMapping("{id}")
	public ResponseEntity<List<ApplicationResponseDTO>> getApplicationByID(@PathVariable Long id) {
		return ResponseEntity.ok(applicationService.getApplicationById(id));
	}

	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {

		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);

		List<ApplicationResponseDTO> applicationList = applicationService.getAllApplication();
		PDFGenerator generator = new PDFGenerator();
		generator.setApplicationList(applicationList);
		generator.generate(response);

	}

	@GetMapping("/pdf/{id}")
	public void generatePdfByApplicationId(@PathVariable("id") Long id, HttpServletResponse response)
			throws DocumentException, IOException {

		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);

		List<ApplicationResponseDTO> applicationList = applicationService.getApplicationById(id);
		PDFGenerator generator = new PDFGenerator();
		generator.setApplicationList(applicationList);
		generator.generate(response);

	}

	@PostMapping
	public ResponseEntity<ApplicationResponseDTO> createApplication(
			@Valid @RequestBody ApplicationRequestDTO applicationRequestDTO) {
		return ResponseEntity.ok(applicationService.createApplication(applicationRequestDTO));
	}

	@PutMapping("{id}")
	public ResponseEntity<ApplicationResponseDTO> updateApplication(
			@Valid @RequestBody ApplicationRequestDTO applicationRequestDTO, @PathVariable Long id) {
		applicationService.updateApplication(id, applicationRequestDTO);
		return ResponseEntity.noContent().build();
	}
}
