import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        boolean moreRecords = true;

        do {
            // Collect data from user
            String firstName = SafeInput.getNonZeroLenString(in, "Enter your first name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter your last name");
            String idNumber = String.format("%06d", SafeInput.getInt(in, "Enter your ID number (6 digits)"));
            String email = SafeInput.getNonZeroLenString(in, "Enter your email");
            int yearOfBirth = SafeInput.getInt(in, "Enter your year of birth");

            // Create CSV record
            String record = firstName + ", " + lastName + ", " + idNumber + ", " + email + ", " + yearOfBirth;
            records.add(record);

            // Check if the user wants to add more records
            moreRecords = SafeInput.getYNConfirm(in, "Do you want to add another record?");
        } while (moreRecords);

        // Prompt for file name and save to CSV
        String fileName = SafeInput.getNonZeroLenString(in, "Enter the filename to save (with .csv extension)");

        try (FileWriter writer = new FileWriter("src/" + fileName)) {
            for (String record : records) {
                writer.write(record + "\n");
            }
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the data.");
            e.printStackTrace();
        }
    }
}