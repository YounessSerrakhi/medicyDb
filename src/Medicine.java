import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Medicine {
    private String id;
    private String name;
    private String form;
    private String marketingStatus;
    private boolean commercialized;
    private LocalDate approvalDate;
    private String manufacturer;
    private float price;

    // constructor
    public Medicine(String id, String name, String form, String marketingStatus,
                    boolean commercialized, LocalDate approvalDate, String manufacturer,float price) {
        this.id = id;
        this.name = name;
        this.form = form;
        this.marketingStatus = marketingStatus;
        this.commercialized = commercialized;
        this.approvalDate = approvalDate;
        this.manufacturer = manufacturer;
        this.price=price;
    }

    // getters and setters (omitted for brevity)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getters and setters for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getters and setters for form
    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    // Getters and setters for marketingStatus
    public String getMarketingStatus() {
        return marketingStatus;
    }

    public void setMarketingStatus(String marketingStatus) {
        this.marketingStatus = marketingStatus;
    }

    // Getters and setters for commercialized
    public boolean isCommercialized() {
        return commercialized;
    }

    public void setCommercialized(boolean commercialized) {
        this.commercialized = commercialized;
    }

    // Getters and setters
    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    // Getters and setters for manufacturer
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public float getPrice() {
        return price;
    }

    public static List<Medicine> parseMedicineList(String fileName) throws FileNotFoundException {
        List<Medicine> medicines = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] fields = line.split("\t");
                String id = fields[0];
                String[] fieldsName = fields[1].split(", ");
                String name = fieldsName[0];
                String form = fields[2];
                String route = fields[3];
                String marketingStatus = fields[4];
                String approvalProcess = fields[5];
                boolean commercialized = fields[6].equals("Commercialisée");
                LocalDate approvalDate = LocalDate.parse(fields[7], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String availabilityWarning = fields[8];
                String manufacturer = fields[9];
                boolean discontinued = fields[10].equals("Non");
                Random random = new Random();

                // Generate a random price within the specified range
                float price = 5.22f + (198.59f - 5.22f) * random.nextFloat();

                Medicine medicine = new Medicine(id, name, form, marketingStatus,
                        commercialized, approvalDate, manufacturer,price);
                medicines.add(medicine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return medicines;
        }



    public static void main(String[] args) {

        try {
            List<Medicine> medicines = Medicine.parseMedicineList("D:\\Coding\\JAVA\\src\\medicineDB\\Medicines1.txt");
            for (Medicine medicine : medicines) {
                System.out.println("ID: " + medicine.getId());
                System.out.println("Name: " + medicine.getName());
                System.out.println("Form: " + medicine.getForm());
                System.out.println("Marketing Status: " + medicine.getMarketingStatus());
                System.out.println("Commercialized: " + medicine.isCommercialized());
                System.out.println("Approval Date: " + medicine.getApprovalDate());
                System.out.println("Manufacturer: " + medicine.getManufacturer());
                System.out.println("****"+medicine.getPrice());
                System.out.println();

            }
            DAO dao = new DAO();
           dao.add(medicines);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
       }  catch (SQLException e) {
          throw new RuntimeException(e);
       }


    }
}
