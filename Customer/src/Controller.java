import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    static Scanner sc = new Scanner(System.in);
    static List<Customer> customers = new ArrayList<Customer>();
    public static void readFile() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream("Customer.txt");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine())!= null){
                if(line.isEmpty()){
                    continue;
                }
                Customer ctm = new Customer();
                ctm.parse(line);
                customers.add(ctm);
            }
        } catch (FileNotFoundException e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            {
                if(fis!=null) {
                    try {
                        fis.close();
                    }
                    catch (IOException e) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                if(isr!=null) {
                    try {
                        isr.close();
                    } catch (IOException e) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                if(br!=null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        }
    }

    public static void saveFile() {
        clearFile("Customer.txt");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("Customer.txt", true);
            for (Customer c : customers) {
                String line = c.getFileLine();
                byte[] bytes = line.getBytes();
                fos.write(bytes);
            }
        } catch (FileNotFoundException e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(fos!=null) {
                try {
                    fos.close();
                }
                catch (IOException e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

    }

    public static void inputCustomer() {
        System.out.println("Số khách hàng muốn thêm: ");
        int add = sc.nextInt();
        sc.nextLine();
        for ( int i =0; i<add;i++){
            Customer customer = new Customer();
            customer.inputName();
            customer.inputEmail();
            customer.inputSdt();
            int j =0;
            for (Customer c : customers) {
                if(Objects.equals(customer.getPhone(), c.getPhone())){
                    j++;
                }
            }
            if(j==0)
            {
                customers.add(customer);
            }
            else System.out.println("Đã tồn tại số điện thoại");
        }
    }


    public static void displayCustomer() {
        for(Customer customer : customers){
            customer.display();
        }
    }

    public static void searchbyPhone() {
        System.out.println("Số điện thoại khách hàng cần tìm: ");
        String sdt = sc.nextLine();
        int i = 0;
        for(Customer customer : customers){
            if (customer.getPhone().equals(sdt)){
                i++;
                System.out.println(customer);
            }
        }
        if(i == 0) System.out.println("Không tìm thấy khách hàng có số điện thoại "+sdt);
    }

    public static void editCustomer() {
        System.out.println("Thông tin khách hàng cần sửa đổi : \n" +
                "1. Tên: \n" +
                "2. Email: \n" +
                "3. Số điện thoại: \n");
        int choose = sc.nextInt();
        sc.nextLine();
        switch (choose) {
            case 1:
                System.out.println("Tên khách hàng cần sửa đổi: ");
                ArrayList<Customer> ctms = new ArrayList<>();
                String name = sc.nextLine();
                for (Customer customer : customers) {
                    if (customer.getName().equals(name)) {
                        ctms.add(customer);
                    }
                }
                if (ctms.isEmpty()) System.out.println("Không có khách hàng tên " + name);
                if (ctms.size() == 1) {
                    int i;
                    do {
                        menuMini();
                        i = sc.nextInt();
                        sc.nextLine();
                        switch (i) {
                            case 1:
                                ctms.get(0).inputName();
                                break;
                            case 2:
                                ctms.get(0).inputEmail();
                                break;
                            case 3:
                                ctms.get(0).inputSdt();
                                break;
                            case 4:
                                break;
                            default:
                                break;
                        }

                    } while (i != 4);
                }
                if (ctms.size() > 1) {
                    for (Customer customer : ctms) {
                        customer.display();
                    }
                    System.out.println("Số điện thoại khách hàng bạn muốn thay đổi");
                    String sdt = sc.nextLine();
                    int j = 0;
                    for (Customer customer : ctms) {
                        if (customer.getPhone().equals(sdt)) {
                            int i;
                            j++;
                            do {
                                menuMini();
                                i = sc.nextInt();
                                sc.nextLine();
                                switch (i) {
                                    case 1:
                                        ctms.get(0).inputName();
                                        break;
                                    case 2:
                                        ctms.get(0).inputEmail();
                                        break;
                                    case 3:
                                        ctms.get(0).inputSdt();
                                        break;
                                    case 4:
                                        break;
                                    default:
                                        break;
                                }

                            } while (i != 4);
                        }
                        if(j==0) System.out.println("Không tìm thấy khách hàng phù hợp");
                    }
                }
                break;
            case 2:
                System.out.println("Email khách hàng cần sửa đổi: ");
                ArrayList<Customer> ctms1 = new ArrayList<>();
                String email = sc.nextLine();
                for (Customer customer : customers) {
                    if (customer.getEmail().equals(email)) {
                        ctms1.add(customer);
                    }
                }
                if (ctms1.isEmpty()) System.out.println("Không có khách hàng có Email " + email);
                if (ctms1.size() == 1) {
                    int i;
                    do {
                        menuMini();
                        i = sc.nextInt();
                        sc.nextLine();
                        switch (i) {
                            case 1:
                                ctms1.get(0).inputName();
                                break;
                            case 2:
                                ctms1.get(0).inputEmail();
                                break;
                            case 3:
                                ctms1.get(0).inputSdt();
                                break;
                            case 4:
                                break;
                            default:
                                break;
                        }

                    } while (i != 4);
                }
                if (ctms1.size() > 1) {
                    for (Customer customer : ctms1) {
                        customer.display();
                    }
                    System.out.println("Số điện thoại khách hàng bạn muốn thay đổi");
                    String sdt = sc.nextLine();
                    int j = 0;
                    for (Customer customer : ctms1) {
                        if (customer.getPhone().equals(sdt)) {
                            j++;
                            int i;
                            do {
                                menuMini();
                                i = sc.nextInt();
                                sc.nextLine();
                                switch (i) {
                                    case 1:
                                        ctms1.get(0).inputName();
                                        break;
                                    case 2:
                                        ctms1.get(0).inputEmail();
                                        break;
                                    case 3:
                                        ctms1.get(0).inputSdt();
                                        break;
                                    case 4:
                                        break;
                                    default:
                                        break;
                                }

                            } while (i != 4);
                        }
                    }
                    if(j==0) System.out.println("Không tìm thấy khách hàng phù hợp");
                }
                break;
            case 3:
                System.out.println("Số điện thoại khách hàng cần sửa đổi: ");
                int j = 0;
                String sdt = sc.nextLine();
                for (Customer customer : customers) {
                    if (customer.getPhone().equals(sdt)) {
                        j++;
                        int i;
                        do {
                            menuMini();
                            i = sc.nextInt();
                            sc.nextLine();
                            switch (i) {
                                case 1:
                                    customer.inputName();
                                    break;
                                case 2:
                                    customer.inputEmail();
                                    break;
                                case 3:
                                    customer.inputSdt();
                                    break;
                                case 4:
                                    break;
                                default:
                                    break;
                            }

                        } while (i != 4);
                    }
                    if(j==0) System.out.println("Không tìm thấy khách hàng phù hợp");
                }
                break;
            default :
                break;
        }
    }


    public static void deleteCustomer() {
        System.out.println("Thông tin khách hàng cần xóa : \n" +
                "1. Tên: \n" +
                "2. Email: \n" +
                "3. Số điện thoại: \n");
        int choose = sc.nextInt();
        sc.nextLine();
        switch (choose) {
            case 1:System.out.println("Tên khách hàng cần xóa: ");
                ArrayList<Customer> ctm1 = new ArrayList<>();
                int j=0;
                String name = sc.nextLine();
                for(Customer customer : customers){
                    if (customer.getName().equals(name)){
                        ctm1.add(customer);
                    }
                    if(ctm1.isEmpty()){
                        System.out.println("Không có khách hàng tên " + name);
                    }
                    if(ctm1.size()==1){
                        customers.remove(ctm1.get(0));
                    }
                    if(ctm1.size()>1){
                        for (Customer c : ctm1){
                            c.display();
                        }
                        System.out.println("Số điện thoại khách hàng bạn muốn xóa");
                        String sdt = sc.nextLine();
                        int z = 0;
                        for (Customer c : ctm1) {
                            j++;
                            if (c.getPhone().equals(sdt)) {
                                customers.remove(c);
                                System.out.println("Đã xóa");
                            }
                        }
                        if(j==0) System.out.println("Không tìm thấy khách hàng phù hợp");
                    }
                }
                break;
            case 2:System.out.println("Email khách hàng cần xóa: ");
                ArrayList<Customer> ctm2 = new ArrayList<>();
                int m=0;
                String email = sc.nextLine();
                for(Customer customer : customers){
                    if (customer.getName().equals(email)){
                        ctm2.add(customer);
                    }
                    if(ctm2.isEmpty()){
                        System.out.println("Không có khách hàng có email " + email);
                    }
                    if(ctm2.size()==1){
                        customers.remove(ctm2.get(0));
                    }
                    if(ctm2.size()>1){
                        for (Customer c : ctm2){
                            c.display();
                        }
                        System.out.println("Số điện thoại khách hàng bạn muốn xóa");
                        String sdt = sc.nextLine();
                        int z = 0;
                        for (Customer c : ctm2) {
                            m++;
                            if (c.getPhone().equals(sdt)) {
                                customers.remove(c);
                                System.out.println("Đã xóa");
                            }
                        }
                        if(m==0) System.out.println("Không tìm thấy khách hàng phù hợp");
                    }
                }
                break;
            case 3: System.out.println("Số điện thoại khách hàng cần xóa: ");
                String sdt = sc.nextLine();
                int n =0;
                for(Customer customer : customers){
                    if (customer.getPhone().equals(sdt)){
                        customers.remove(customer);
                        n++;
                    }
                    if(n==0) System.out.println("Không có khách hàng phù hợp");
                }
                break;
            default:
                System.out.println("Nhập sai!");
                break;
        }
    }
    public static void menuMini(){
        System.out.println("Sửa đổi : \n" +
                "1. Tên: \n" +
                "2. Email: \n" +
                "3. Số điện thoại: \n" +
                "4. Thoát");
    }
    public static void clearFile(String filePath) {
        BufferedWriter writer = null;
        try {
            // Tạo đối tượng BufferedWriter để ghi vào file
            writer = new BufferedWriter(new FileWriter("Customer.txt"));

            // Ghi nội dung rỗng vào file
            writer.write("");

            // Đóng BufferedWriter
            writer.close();

            System.out.println("Nội dung của file đã được xóa.");
        } catch (IOException e) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
