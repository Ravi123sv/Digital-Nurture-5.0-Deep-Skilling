interface Document {
    void open();
}
class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening word document...[.docx ]");

    }
}

class PDFDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF document...[.docx ]");
    }
}

class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel document...[.docx ]");
    }
}

abstract class DocumentFactory {
    public abstract Document createDocument();
}

class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}
class PDFDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PDFDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}
public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        System.out.println("FactoryMethodPatternExample");
        DocumentFactory wordDocumentFactory = new WordDocumentFactory();
        Document myWordDocument = wordDocumentFactory.createDocument();
        myWordDocument.open();

        DocumentFactory pdfDocumentFactory = new PDFDocumentFactory();
        Document myPDFDocument = pdfDocumentFactory.createDocument();
        myPDFDocument.open();

        DocumentFactory excelDocumentFactory = new ExcelDocumentFactory();
        Document myExcelDocument = excelDocumentFactory.createDocument();
        myExcelDocument.open();
    }
}