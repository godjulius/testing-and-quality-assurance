import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

public class TinhGiaDien {
    static final double[][] electricityPrice = {
            { 0, 50, 1806 },
            { 50, 100, 1866 },
            { 100, 200, 2167 },
            { 200, 300, 2729 },
            { 300, 400, 3050 },
            { 400, 100000, 3151 }
    };
    static Map<String, Double> discount = new HashMap<String, Double>();

    public static double tinhGiaDien(double soDien, String type) {
        if (soDien < 0 || !discount.containsKey(type)) {
            return -1;
        }
        double giaDien = 0.0;
        for (int i = 0; i < electricityPrice.length; i++) {
            if (soDien > electricityPrice[i][1]) {
                giaDien += (electricityPrice[i][1] - electricityPrice[i][0]) * electricityPrice[i][2];
            } else {
                giaDien += (soDien - electricityPrice[i][0]) * electricityPrice[i][2];
                break;
            }
        }
        giaDien = giaDien * (1.0 - discount.get(type));
        return giaDien;
    }

    public static void main(String[] args) {
        discount.put("van phong", 0.1);
        discount.put("nha may", 0.2);
        discount.put("co ban", 0.0);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isValid = false;
        double soDien = 0;
        String type = "";
        String str = "";
        // Nhập số điện tiêu thụ; min 0; max 100000
        do {
            System.out.print("Nhap so dien tieu thu (kWh): ");
            try {
                str = reader.readLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            if (!str.matches("-?\\d+(\\.\\d+)?")) {
                System.out.println("Khong hop le, moi nhap lai!");
            } else {
                soDien = Double.parseDouble(str);
                isValid = true;
            }
        } while (!isValid);
        isValid = false;
        System.out.println("Nhap loai tieu dung: ");
        try {
            type = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        double giaDien = tinhGiaDien(soDien, type);
        // Làm tròn giá điện đến 3 chữ số sau dấu phẩy
        String formattedNumber = String.format("%.3f", giaDien);
        System.out.println("Gia dien phai tra: " + formattedNumber + " VND.");
    }
}