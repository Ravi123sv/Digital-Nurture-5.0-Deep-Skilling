class Book {
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }
}

public class LibrarySearch {

    public static int linearSearch(Book[] books, String targetTitle) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].title.equalsIgnoreCase(targetTitle)) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(Book[] books, String targetTitle) {
        int low = 0;
        int high = books.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = books[mid].title.compareToIgnoreCase(targetTitle);

            if (comparison == 0) return mid;
            if (comparison < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Book[] library = {
                new Book(1, "A Tale of Two Cities", "Charles Dickens"),
                new Book(2, "Brave New World", "Aldous Huxley"),
                new Book(3, "Catch-22", "Joseph Heller"),
                new Book(4, "Dune", "Frank Herbert")
        };

        System.out.println("--- Library Search System ---");

        String searchTitle = "Catch-22";

        int linearResult = linearSearch(library, searchTitle);
        System.out.println("Linear Search: Found '" + searchTitle + "' at index " + linearResult);

        int binaryResult = binarySearch(library, searchTitle);
        System.out.println("Binary Search: Found '" + searchTitle + "' at index " + binaryResult);
    }
}