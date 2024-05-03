import java.util.Scanner;


public class Customer {
    String name;
    String email;
    String phone;

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


    public Customer() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // nhập thông tin khách hàng
    public void inputName() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Nhập tên: ");
            name = sc.nextLine();
            if (Util.isValidName(name)) {
                break;
            } else System.out.println("Tên không được để trống\n" +
                    "Nhập lại tên: ");
        }
    }

    public void inputEmail() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Nhập Email: ");
            email = sc.nextLine();
            if (Util.isValidEmail(email)) {
                break;
            } else System.out.println("Email không hợp lệ\n");
        }
    }

    public void inputSdt() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Nhập số điện thoại: ");
            phone = sc.nextLine();
            if (Util.isValidPhoneNumber(phone)) {
                break;
            } else System.out.println("Số điện không hợp lệ\n");
        }
    }


    //chia dữ liệu từ file thành mảng
    public void parse(String line) {
        String[] i = line.split(",");
        try {
            name = i[0];
            email = i[1];
            phone = i[2];
        } catch (ArrayIndexOutOfBoundsException e)  // catch ngoại lệ để khi tự nhập từ file có thể sai
        {
        }
    }

    public void display() {
        System.out.println(this);
    }

    // dạng string khi lưu xuống file
    public String getFileLine() {
        return  name + "," + email + "," + phone + "\n";
    }

    public String toString() {
        return String.format("%-20s %-30s %-10s%n", name, email, phone);
    }
}
