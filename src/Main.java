import java.util.ArrayList;
import java.util.Scanner;

class MenuItem {
    int id;
    String name;
    double price;

    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + ". " + name + " - Rp " + price;
    }
}

class OrderItem {
    MenuItem menuItem;
    int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return menuItem.price * quantity;
    }
}

class RestaurantOrderApp {
    private ArrayList<MenuItem> menu = new ArrayList<>();
    private ArrayList<OrderItem> orders = new ArrayList<>();

    public RestaurantOrderApp() {
        initializeMenu();
    }

    private void initializeMenu() {
        menu.add(new MenuItem(1, "Nasi Goreng", 25000));
        menu.add(new MenuItem(2, "Mie Ayam", 20000));
        menu.add(new MenuItem(3, "Ayam Bakar", 30000));
        menu.add(new MenuItem(4, "Es Teh", 5000));
        menu.add(new MenuItem(5, "Es Jeruk", 7000));
    }

    public void showMenu() {
        System.out.println("Daftar Menu:");
        for (MenuItem item : menu) {
            System.out.println(item);
        }
    }

    public void addOrder(int menuId, int quantity) {
            MenuItem item = getMenuItemById(menuId);
            if (item != null) {
                orders.add(new OrderItem(item, quantity));
                System.out.println(quantity + "x " + item.name + " ditambahkan ke pesanan.");
            } else {
                System.out.println("Menu tidak ditemukan.");
            }
        }


    public void removeOrder(int menuId) {
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).menuItem.id == menuId) {
                    System.out.println("Pesanan " + orders.get(i).menuItem.name + " dihapus.");
                    orders.remove(i);
                    return;
                }
            }
            System.out.println("Pesanan tidak ditemukan.");
        }

    private MenuItem getMenuItemById(int id) {
        for (MenuItem item : menu) {
            if (item.id == id) {
                return item;
            }
        }
        return null;
    }

     public void showOrderSummary() {
             System.out.println("\nRingkasan Pesanan:");
             double total = 0;
             for (OrderItem order : orders) {
                 double itemTotal = order.getTotalPrice();
                 total += itemTotal;
                 System.out.println(order.quantity + "x " + order.menuItem.name + " - Rp " + itemTotal);
             }
             System.out.println("Total Harga: Rp " + total);
         }

    public void confirmPayment() {
        showOrderSummary();
        System.out.println("Apakah Anda ingin membayar? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.next();
        if (confirmation.equalsIgnoreCase("y")) {
            System.out.println("Pembayaran berhasil. Terima kasih!");
        } else {
            System.out.println("Pembayaran dibatalkan.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestaurantOrderApp app = new RestaurantOrderApp();
        boolean running = true;

        System.out.println("Selamat datang di Restoran!");

        while (running) {
            System.out.println("\nPilih opsi:");
            System.out.println("1. Tampilkan Menu");
            System.out.println("2. Tambah Pesanan");
            System.out.println("3. Hapus Pesanan");
            System.out.println("4. Lihat Ringkasan Pesanan");
            System.out.println("5. Konfirmasi Pembayaran dan Keluar");

            System.out.print("Masukkan pilihan: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    app.showMenu();
                    break;
                case 2:
                    app.showMenu();
                    System.out.print("Pilih menu (ID): ");
                    int menuId = scanner.nextInt();
                    System.out.print("Jumlah porsi: ");
                    int quantity = scanner.nextInt();
                    app.addOrder(menuId, quantity);
                    break;
                case 3:
                    System.out.print("Masukkan ID menu yang ingin dihapus dari pesanan: ");
                    int removeId = scanner.nextInt();
                    app.removeOrder(removeId);
                    break;
                case 4:
                    app.showOrderSummary();
                    break;
                case 5:
                    app.confirmPayment();
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        scanner.close();
    }
}