import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Barang barang = null;
        Pembeli pembeli = null;
        Transaksi transaksi = null;
        int pilihan_user = 0, pilihan = 0;
        String role; // role pengguna (Admin atau Kasir)
        boolean isRunning = true; // apakah aplikasi masih berjalan

        while (isRunning) {
            // Pilih role pengguna
            System.out.println("Pilih role pengguna: ");
            System.out.println("1. Admin");
            System.out.println("2. Kasir");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan: ");
            pilihan_user = input.nextInt();
            input.nextLine();
            if (pilihan_user == 1) {
                role = "Admin";
            } else if (pilihan_user == 2) {
                role = "Kasir";
            } else if (pilihan_user == 3) {
                System.out.println("Terima kasih telah menggunakan aplikasi.");
                break; // Keluar dari perulangan utama
            } else {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                continue; // Kembali ke awal perulangan
            }

            // Menu utama
            boolean isLoggedIn = true; // pengguna telah memilih role dan masuk ke menu utama
            while (isLoggedIn) {
                System.out.println("== Menu ==");
                System.out.println("---Admin Menu---");
                System.out.println("1. Tambah Barang");
                System.out.println("2. Hapus Barang");
                System.out.println("3. Tampilkan Barang");
                System.out.println("4. Update Barang");
                System.out.println("---Kasir Menu---");
                System.out.println("5. Tambah Pembeli");
                System.out.println("6. Transaksi");
                System.out.println("----------------");
                System.out.println("7. Log Out");
                System.out.print("Masukkan pilihan: ");
                pilihan = input.nextInt();
                input.nextLine(); // Consume newline

                switch (pilihan) {
                    case 1:
                        // Tambah barang
                        if (role.equals("Admin")) {
                            System.out.println("==Tambah Barang==");
                            System.out.print("Masukkan kode barang: ");
                            int kd = input.nextInt();
                            System.out.print("Masukkan nama barang: ");
                            String nm = input.next();
                            System.out.print("Masukkan harga barang: ");
                            int hg = input.nextInt();
                            System.out.print("Masukkan stok barang: ");
                            int st = input.nextInt();
                            barang = new Barang(kd, nm, hg, st);
                            System.out.println("Barang berhasil ditambahkan.");
                        } else {
                            System.out.println("Hanya Admin yang dapat menambah barang.");
                        }
                        break;
                    case 2:
                        // Hapus barang
                        if (role.equals("Admin")) {
                            System.out.println("==Hapus Barang==");
                            System.out.print("Masukkan nama barang yang ingin dihapus: ");
                            String nm_hapus = input.nextLine();
                            if (nm_hapus.equalsIgnoreCase(barang.getNama_barang())) {
                                barang.hapusBarang();
                            }
                            System.out.println("Barang berhasil dihapus.");
                        } else {
                            System.out.println("Hanya Admin yang dapat menghapus barang.");
                        }
                        break;
                    case 3:
                        // Tampilkan informasi barang
                        System.out.println("==Tampilkan Barang==");
                        if (barang != null) {
                            barang.tampilkanBarang();
                        } else {
                            System.out.println("Belum ada barang yang ditambahkan.");
                        }
                        break;
                    case 4:
                        // Update informasi barang
                        if (role.equals("Admin")) {
                            System.out.println("==Update Barang==");
                            System.out.print("Masukkan nama barang yang ingin diupdate: ");
                            String nm = input.nextLine();
                            if (barang != null && nm.equalsIgnoreCase(barang.getNama_barang())) {
                                System.out.print("Masukkan harga barang baru: ");
                                int hg_baru = input.nextInt();
                                System.out.print("Masukkan stok barang baru: ");
                                int st_baru = input.nextInt();
                                barang.setHarga(hg_baru);
                                barang.setStok(st_baru);
                                System.out.println("Barang berhasil diupdate.");
                            } else {
                                System.out.println("Barang tidak ditemukan.");
                            }
                        } else {
                            System.out.println("Hanya Admin yang dapat mengupdate barang.");
                        }
                        break;
                    case 5:
                        // Tambah pembeli
                        if (role.equals("Kasir")) {
                            System.out.println("==Tambah Pembeli==");
                            System.out.print("Masukkan ID pembeli: ");
                            int idPembeli = input.nextInt();
                            System.out.print("Masukkan nama pembeli: ");
                            String namaPembeli = input.next();
                            System.out.print("Masukkan alamat pembeli: ");
                            String alamatPembeli = input.next();
                            pembeli = new Pembeli(idPembeli, namaPembeli, alamatPembeli);
                            System.out.println("Pembeli berhasil ditambahkan.");
                        } else {
                            System.out.println("Hanya Kasir yang dapat menambah pembeli.");
                        }
                        break;
                    case 6:
                        // Transaksi
                        if (role.equals("Kasir")) {
                            System.out.println("==Transaksi==");
                            if (pembeli != null && barang != null) {
                                System.out.print("Masukkan jumlah barang yang ingin dibeli: ");
                                int jumlah = input.nextInt();
                                input.nextLine();

                                if (jumlah > barang.getStok()) {
                                    System.out.println("Stok tidak mencukupi.");
                                    break;
                                }

                                // Inisialisasi objek Transaksi dan panggil metode TambahTransaksi
                                transaksi = new Transaksi();
                                transaksi.TambahTransaksi(barang, pembeli, jumlah); 

                                // Kurangi stok barang
                                barang.setStok(barang.getStok() - jumlah);

                                // Cetak informasi transaksi
                                transaksi.cetakTransaksi();
                            } else {
                                System.out.println("Pembeli atau barang belum tersedia.");
                            }
                        } else {
                            System.out.println("Hanya Kasir yang dapat melakukan transaksi.");
                        }
                        break;
                    case 7:
                        // Log Out
                        System.out.println("Log Out berhasil. Kembali ke menu utama.");
                        isLoggedIn = false; // Keluar dari perulangan menu
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
                }
            }
        }

        input.close();
    }
}