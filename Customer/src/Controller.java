import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String, Customer> customers = new HashMap<>();

    public static void readFile() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            File file = new File("Customer.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fis = new FileInputStream("Customer.txt");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                Customer ctm = new Customer();
                ctm.parse(line);
                customers.put(ctm.phone, ctm);
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                if (isr != null) {
                    try {
                        isr.close();
                    } catch (IOException e) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                if (br != null) {
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
        try (FileOutputStream fos = new FileOutputStream("Customer.txt")) {
            for (Map.Entry<String, Customer> entry : customers.entrySet()) {
                String line = entry.getValue().getFileLine();
                byte[] bytes = line.getBytes();
                fos.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void inputCustomer() {
        System.out.println("Số khách hàng muốn thêm: ");
        int add = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < add; i++) {
            Customer customer = new Customer();
            customer.inputName();
            customer.inputEmail();
            customer.inputSdt();
            boolean j = true;
            if (customers.containsKey(customer.phone)) {
                while (j) {
                    System.out.println("Số điện thoại đã tồn tại");
                    System.out.println("1. Nhập lại số điện thoại\n" +
                            "2. Thoát\n" +
                            "Chọn: ");
                    int select = sc.nextInt();
                    sc.nextLine();
                    switch (select) {
                        case 1:
                            customer.inputSdt();
                            break;
                        case 2:
                            break;
                        default:
                            break;
                    }
                    if (!customers.containsKey(customer.phone) || select != 1) j = false;
                }
            }
            customers.put(customer.phone, customer);
        }
        Controller.saveFile();
    }


    public static void searchbyPhone() {
        System.out.println("Số điện thoại khách hàng cần tìm: ");
        String sdt = sc.nextLine();
        int i = 0;
        if (customers.containsKey(sdt)) {
            System.out.println(customers.get(sdt));
        } else System.out.println("Không tìm thấy khách hàng có số điện thoại " + sdt);
    }

    public static void editCustomer() {
        System.out.println("Thông tin khách hàng cần sửa đổi : \n" +
                "1. Tên: \n" +
                "2. Email: \n" +
                "3. Số điện thoại: ");
        int choose = sc.nextInt();
        sc.nextLine();
        ArrayList<Customer> customerslist = new ArrayList<>();
        switch (choose) {
            case 1:
                System.out.println("Tên khách hàng cần sửa đổi: ");
                String name = sc.nextLine();
                for (Map.Entry<String, Customer> entry : customers.entrySet()) {
                    if (entry.getValue().name.equals(name)) {
                        customerslist.add(entry.getValue());
                    }
                }
                if (customerslist.size() == 0) System.out.println("Không có khách hàng tên " + name);
                if (customerslist.size() == 1) {
                    int i;
                    do {
                        Main.menuMini();
                        i = sc.nextInt();
                        sc.nextLine();
                        switch (i) {
                            case 1:
                                customers.get(customerslist.get(0).phone).inputName();
                                break;
                            case 2:
                                customers.get(customerslist.get(0).phone).inputEmail();
                                break;
                            case 3:
                                Customer newCustomer = new Customer();
                                newCustomer.name = customerslist.get(0).name;
                                newCustomer.email = customerslist.get(0).email;
                                newCustomer.inputSdt();
                                customers.put(newCustomer.phone, newCustomer);
                                customers.remove(customerslist.get(0).phone);
                                break;
                            case 4:
                                break;
                            default:
                                break;
                        }

                    } while (i != 4);
                }
                if (customerslist.size() > 1) {
                    for (Customer customer : customerslist) {
                        customer.display();
                    }
                    System.out.println("Số điện thoại khách hàng bạn muốn thay đổi");
                    String sdt = sc.nextLine();
                    int j = 0;
                    for (Customer customer : customerslist) {
                        if (customer.getPhone().equals(sdt)) {
                            int i;
                            j++;
                            do {
                                Main.menuMini();
                                i = sc.nextInt();
                                sc.nextLine();
                                switch (i) {
                                    case 1:
                                        customers.get(sdt).inputName();
                                        break;
                                    case 2:
                                        customers.get(sdt).inputEmail();
                                        break;
                                    case 3:
                                        Customer newCustomer = new Customer();
                                        newCustomer.name = customers.get(sdt).name;
                                        newCustomer.email = customers.get(sdt).email;
                                        newCustomer.inputSdt();
                                        customers.put(newCustomer.phone, newCustomer);
                                        customers.remove(sdt);
                                        break;
                                    case 4:
                                        break;
                                    default:
                                        break;
                                }

                            } while (i != 4);
                        }
                        if (j == 0) System.out.println("Không tìm thấy khách hàng phù hợp");
                    }
                }
                break;
            case 2:
                System.out.println("Email khách hàng cần thay đổi: ");
                String email = sc.nextLine();
                for (Map.Entry<String, Customer> entry : customers.entrySet()) {
                    if (entry.getValue().email.equals(email)) {
                        customerslist.add(entry.getValue());
                    }
                }
                if (customerslist.size() == 0) System.out.println("Không có khách hàng có Email " + email);
                if (customerslist.size() == 1) {
                    int i;
                    do {
                        Main.menuMini();
                        i = sc.nextInt();
                        sc.nextLine();
                        switch (i) {
                            case 1:
                                customerslist.get(0).inputName();
                                break;
                            case 2:
                                customerslist.get(0).inputEmail();
                                break;
                            case 3:
                                Customer newCustomer = new Customer();
                                newCustomer.name = customerslist.get(0).name;
                                newCustomer.email = customerslist.get(0).email;
                                newCustomer.inputSdt();
                                customers.put(newCustomer.phone, newCustomer);
                                customers.remove(customerslist.get(0).phone);
                                break;
                            case 4:
                                break;
                            default:
                                break;
                        }

                    } while (i != 4);
                }
                if (customerslist.size() > 1) {
                    for (Customer customer : customerslist) {
                        customer.display();
                    }
                    System.out.println("Số điện thoại khách hàng bạn muốn thay đổi");
                    String sdt = sc.nextLine();
                    int j = 0;
                    for (Customer customer : customerslist) {
                        if (customer.getPhone().equals(sdt)) {
                            int i;
                            j++;
                            do {
                                Main.menuMini();
                                i = sc.nextInt();
                                sc.nextLine();
                                switch (i) {
                                    case 1:
                                        customers.get(sdt).inputName();
                                        break;
                                    case 2:
                                        customers.get(sdt).inputEmail();
                                        break;
                                    case 3:
                                        Customer newCustomer = new Customer();
                                        newCustomer.name = customers.get(sdt).name;
                                        newCustomer.email = customers.get(sdt).email;
                                        newCustomer.inputSdt();
                                        customers.put(newCustomer.phone, newCustomer);
                                        customers.remove(sdt);
                                        break;
                                    case 4:
                                        break;
                                    default:
                                        break;
                                }

                            } while (i != 4);
                        }
                        if (j == 0) System.out.println("Không tìm thấy khách hàng phù hợp");
                    }
                }
                break;
            case 3:
                System.out.println("Số điện thoại khách hàng cần sửa đổi: ");
                int j = 0;
                String sdt = sc.nextLine();
                if (!customers.containsKey(sdt)) {
                    System.out.println("Không có khách hàng thỏa mãn");
                } else {
                    int i;
                    do {
                        Main.menuMini();
                        i = sc.nextInt();
                        sc.nextLine();
                        switch (i) {
                            case 1:
                                customers.get(sdt).inputName();
                                break;
                            case 2:
                                customers.get(sdt).inputEmail();
                                break;
                            case 3:
                                Customer newCustomer = new Customer();
                                newCustomer.name = customers.get(sdt).name;
                                newCustomer.email = customers.get(sdt).email;
                                newCustomer.inputSdt();
                                customers.put(newCustomer.phone, newCustomer);
                                customers.remove(sdt);
                                break;
                            case 4:
                                break;
                            default:
                                break;
                        }

                    } while (i != 4);
                }
                break;
            default:
                break;
        }
        Controller.saveFile();
    }


    public static void deleteCustomer() {
        System.out.println("Thông tin khách hàng cần xóa : \n" +
                "1. Tên: \n" +
                "2. Email: \n" +
                "3. Số điện thoại: \n");
        int choose = sc.nextInt();
        sc.nextLine();
            ArrayList<Customer> customerslist1 = new ArrayList<>();
            switch (choose) {
                case 1:
                    System.out.println("Tên khách hàng cần xóa: ");
                    String name = sc.nextLine();
                    for (Map.Entry<String, Customer> entry : customers.entrySet()) {
                        if (entry.getValue().name.equals(name)) {
                            customerslist1.add(entry.getValue());
                        }
                    }
                    if (customerslist1.size() == 0) System.out.println("Không có khách hàng tên " + name);
                    if (customerslist1.size() == 1) {
                        customers.remove(customerslist1.get(0).phone);
                    }
                    if (customerslist1.size() > 1) {
                        for (Customer customer : customerslist1) {
                            customer.display();
                        }
                        System.out.println("Số điện thoại khách hàng bạn muốn xóa");
                        String sdt = sc.nextLine();
                        for (Customer customer : customerslist1) {
                            if (customer.getPhone().equals(sdt)) {
                                customers.remove(sdt);
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Tên khách hàng cần xóa: ");
                    String email = sc.nextLine();
                    for (Map.Entry<String, Customer> entry : customers.entrySet()) {
                        if (entry.getValue().email.equals(email)) {
                            customerslist1.add(entry.getValue());
                        }
                    }
                    if (customerslist1.size() == 0) System.out.println("Không có khách hàng có email " + email);
                    if (customerslist1.size() == 1) {
                        customers.remove(customerslist1.get(0).phone);
                    }
                    if (customerslist1.size() > 1) {
                        for (Customer customer : customerslist1) {
                            customer.display();
                        }
                        System.out.println("Số điện thoại khách hàng bạn muốn xóa");
                        String sdt = sc.nextLine();
                        for (Customer customer : customerslist1) {
                            if (customer.getPhone().equals(sdt)) {
                                customers.remove(sdt);
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Số điện thoại khách hàng cần xóa: ");
                    String sdt = sc.nextLine();
                    int n = 0;
                    if (customers.containsKey(sdt)) {
                        customers.remove(sdt);
                    } else System.out.println("Không có khách hàng phù hợp");
                    break;
                default:
                    System.out.println("Nhập sai!");
                    break;
            }
            Controller.saveFile();
        }
}
