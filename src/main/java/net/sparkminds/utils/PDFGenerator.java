package net.sparkminds.utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import lombok.Data;
import net.sparkminds.service.dto.response.ApplicationResponseDTO;

@Data
public class PDFGenerator {

    private List<ApplicationResponseDTO> applicationList;

    public void generate(HttpServletResponse response) throws DocumentException, IOException {

        Document document = new Document(PageSize.A3);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);

        Paragraph paragraph = new Paragraph("Review Information of CV \n\n", fontTiltle);

        PdfPTable table = new PdfPTable(3);

        Paragraph paragraphBody = new Paragraph();
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        for (ApplicationResponseDTO application : applicationList) {
            List<String> infor = application.getProjects().stream().map((test) -> {
                return String.format(
                        "PAST PROJECT \n- Name:  %s \n- Role: %s\n- Duration: %s\n- Mode: %s"
                                + "\n- Team Size: %s\n- Capacity: %s\n- Start Year: %s"
                                + "\n- Link To Repo: %s\n- Link To Live URL: %s \n",
                        test.getNameProject(), test.getRole(), test.getDuration(), test.getEmploymentMode(),
                        test.getTeamSize(), test.getCapacity(), test.getStartYear(), test.getLinkToRepo(),
                        test.getLinkToLiveUrl());
            }).collect(Collectors.toList());
            String project = String.join(" \n", infor);
            Image img = Image.getInstance(
                    String.format("https://avatars.githubusercontent.com/%s", application.getGithubUser()));
//            img.scaleToFit(PageSize.A4.getWidth() / 5, PageSize.A4.getHeight() / 5);

            PdfPCell cellOne = new PdfPCell(new Phrase(project));
            PdfPCell cellTwo = new PdfPCell(
                    new Phrase(String.format("PERSONALINFORMATION \n- Name: %s \n- Github Account: %s \n- Email: %s \n",
                            application.getGithubUser(), application.getName(), application.getEmailAdress())));

            table.addCell(cellTwo);
            table.addCell(cellOne);
            table.addCell(img);

        }
        document.add(table);
        document.add(paragraphBody);
        // Closing the document
        document.close();

    }
}