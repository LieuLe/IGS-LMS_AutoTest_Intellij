package Utils;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public static List<Object[]> getTestDataLogin(String filePath) throws IOException {
        List<Object[]> testData = new ArrayList<>();
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            String username = getCellValue(row.getCell(0));
            String password = getCellValue(row.getCell(1));
            String expectedErrorMessage = getCellValue(row.getCell(2));
            testData.add(new Object[]{username, password, expectedErrorMessage});
        }
        workbook.close();
        file.close();
        return testData;
    }

    private static String getCellValue(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue()); // Chuyển số thành chuỗi
        } else {
            return ""; // hoặc một giá trị mặc định khác
        }
    }

}
