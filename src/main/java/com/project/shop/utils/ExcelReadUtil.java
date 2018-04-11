package com.project.shop.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ExcelReadUtil {
	/**
	 * 엑셀 파일 Read
	 */
	public List<String> readExcelFile(MultipartFile mpf) {
		List<String> list = new ArrayList<>();
		try {
			Workbook workbook = readWorkbook(mpf);

			Sheet datatypeSheet = workbook.getSheetAt(0);

			Iterator<Row> iterator = datatypeSheet.iterator();

			while (iterator.hasNext()) {
				Row currentRow = iterator.next();

				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();

					// getCellTypeEnum shown as deprecated for version 3.15

					// getCellTypeEnum ill be renamed to getCellType starting from version 4.0

					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						list.add(currentCell.getStringCellValue());
						System.out.print(currentCell.getStringCellValue() + "--");
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						list.add(NumberToTextConverter.toText(currentCell.getNumericCellValue()));
						System.out.print(currentCell.getNumericCellValue() + "--");
					}
				}
				System.out.println();
			}
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;

	}
	
	public Workbook readWorkbook(MultipartFile multipartFile) throws InvalidFormatException, IOException {
		verifyFileExtension(multipartFile);
		return multipartFileToWorkbook(multipartFile);
	}

	private void verifyFileExtension(MultipartFile multipartFile) throws InvalidFormatException {
	    if( !isExcelExtension(multipartFile.getOriginalFilename()) ) {
	        throw new InvalidFormatException("This file extension is not verify");
	    }
	}
	 
	private boolean isExcelExtension(String fileName) {
	    return fileName.endsWith("xls") || fileName.endsWith("xlsx");
	}
	 
	private boolean isExcelXls(String fileName) {
	    return fileName.endsWith("xls");
	}
	 
//	private boolean isExcelXlsx(String fileName) {
//	    return fileName.endsWith("xlsx");
//	}
	
	private Workbook multipartFileToWorkbook(MultipartFile multipartFile)
			throws IOException {
		if (isExcelXls(multipartFile.getOriginalFilename())) {
			return new HSSFWorkbook(multipartFile.getInputStream());
		} else {
			return new XSSFWorkbook(multipartFile.getInputStream());
		}
	}
}
