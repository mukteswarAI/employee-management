package org.ems.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.ems.department.model.Department;
import org.ems.department.repository.DepartmentRepository;
import org.ems.employee.model.Employee;
import org.ems.employee.model.Role;
import org.ems.employee.repository.EmployeeRepository;
import org.ems.employee.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// iText PDF Generation Imports
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfReportGenerator {
    private static final Logger logger = LoggerFactory.getLogger(PdfReportGenerator.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public ByteArrayInputStream generateCompanyReport() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Paragraph title = new Paragraph("Company Comprehensive Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add sections
            addRolesSection(document);
            addDepartmentsSection(document);
            addEmployeeStatisticsSection(document);

            document.close();
        } catch (DocumentException e) {
            logger.error("PDF generation error", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void addRolesSection(Document document) throws DocumentException {
        // Fetch roles data
        List<Role> roles = roleRepository.findAll();

        Paragraph roleTitle = new Paragraph("Roles Overview", 
            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
        document.add(roleTitle);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Table Header
        Stream.of("Role Name", "Description", "Employee Count").forEach(headerTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setPhrase(new Phrase(headerTitle));
            table.addCell(header);
        });

        // Add role data
        for (Role role : roles) {
            table.addCell(role.getRoleName());
            table.addCell(role.getRoleDescription());
            table.addCell(String.valueOf(5));
        }

        document.add(table);
    }

    private void addDepartmentsSection(Document document) throws DocumentException {
        // Fetch departments data
        List<Department> departments = departmentRepository.findAll();

        Paragraph deptTitle = new Paragraph("Departments Overview", 
            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
        document.add(deptTitle);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Table Header
        Stream.of("Department Name", "Location", "Employee Count").forEach(headerTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setPhrase(new Phrase(headerTitle));
            table.addCell(header);
        });

        // Add department data
        for (Department dept : departments) {
            table.addCell(dept.getDepartmentName());
            table.addCell(dept.getDescription());
            table.addCell(String.valueOf(5));
        }

        document.add(table);
    }

    private void addEmployeeStatisticsSection(Document document) throws DocumentException {
        Paragraph statsTitle = new Paragraph("Employee Statistics", 
            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
        document.add(statsTitle);

        // Add overall employee statistics
        long totalEmployees = employeeRepository.count();
        long activeEmployees = 5;
//        long activeEmployees = employeeRepository.countByStatus("ACTIVE");
        long maleEmployees = 5;
//        long maleEmployees = employeeRepository.countByGender("MALE");
//        long femaleEmployees = employeeRepository.countByGender("FEMALE");
        long femaleEmployees = 5;

        Paragraph stats = new Paragraph();
        stats.add(new Phrase("Total Employees: " + totalEmployees));
        stats.add(Chunk.NEWLINE);
        stats.add(new Phrase("Active Employees: " + activeEmployees));
        stats.add(Chunk.NEWLINE);
        stats.add(new Phrase("Male Employees: " + maleEmployees));
        stats.add(Chunk.NEWLINE);
        stats.add(new Phrase("Female Employees: " + femaleEmployees));

        document.add(stats);
    }
}