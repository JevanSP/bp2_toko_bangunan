import java.util.Scanner;
import java.util.ArrayList;

public class MainApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // List Data Master
        ArrayList<Barang> daftarBarang = new ArrayList<>();
        ArrayList<Pembeli> daftarPembeli = new ArrayList<>();
        ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
        ArrayList<Kategori> daftarKategori = new ArrayList<>();

        // Inisialisasi Kategori
        daftarKategori.add(new Kategori("Cat"));
        daftarKategori.add(new Kategori("Pipa"));
        daftarKategori.add(new Kategori("Semen"));
        daftarKategori.add(new Kategori("Bata"));
        daftarKategori.add(new Kategori("Tekiro"));

        // Inisialisasi User
        Admin adminUtama = new Admin("admin", "admin123");
        Kasir kasirUtama = new Kasir("kasir", "kasir789");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== SISTEM POS TOKO BANGUNAN ===");
            System.out.println("1. Login Admin");
            System.out.println("2. Login Kasir");
            System.out.println("3. Keluar");
            System.out.print("Pilih role: ");
            int pilihan_user = input.nextInt();

            if (pilihan_user == 3) break;

            // Validasi Login
            System.out.print("Masukkan Sandi: ");
            String passwordSandi = input.next();
            String roleAktif = "";

            if (pilihan_user == 1) {
                if (adminUtama.cekSandi(passwordSandi)) {
                    roleAktif = "Admin";
                    adminUtama.infoRole(); // Memanggil method infoRole dari subclass Admin
                } else { 
                    System.out.println("Sandi Admin Salah!"); 
                    continue; 
                }
            } else if (pilihan_user == 2) {
                if (kasirUtama.cekSandi(passwordSandi)) {
                    roleAktif = "Kasir";
                    kasirUtama.infoRole(); // Memanggil method infoRole dari subclass Kasir
                } else { 
                    System.out.println("Sandi Kasir Salah!"); 
                    continue; 
                }
            } else continue;

            boolean isLoggedIn = true;
            while (isLoggedIn) {
                System.out.println("\n-- MENU " + roleAktif.toUpperCase() + " --");
                System.out.println("1. Tambah Barang (Admin)");
                System.out.println("2. Hapus Barang (Admin)");
                System.out.println("3. Update Barang (Admin)");
                System.out.println("4. Laporan Keuntungan (Admin)");
                System.out.println("5. Tampilkan Semua Barang");
                System.out.println("6. Tambah Pembeli (Kasir)");
                System.out.println("7. Transaksi Baru (Kasir)");
                System.out.println("8. Riwayat Transaksi");
                System.out.println("9. Log Out");
                System.out.print("Pilihan: ");
                int pilihan = input.nextInt();

                switch (pilihan) {
                    case 1: // Tambah Barang
                        if (roleAktif.equals("Admin")) {
                            System.out.print("Kode: "); int kd = input.nextInt();
                            System.out.print("Nama: "); String nm = input.next();
                            System.out.println("Pilih Kategori:");
                            for (int i = 0; i < daftarKategori.size(); i++) 
                                System.out.println(i + ". " + daftarKategori.get(i).getNamaKategori());
                            System.out.print("Index Kategori: "); int pilKat = input.nextInt();
                            System.out.print("Harga: "); int hg = input.nextInt();
                            System.out.print("Stok: "); int st = input.nextInt();
                            
                            daftarBarang.add(new Barang(kd, nm, hg, st, daftarKategori.get(pilKat)));
                            System.out.println("Barang berhasil disimpan.");
                        } else System.out.println("Akses Ditolak! Khusus Admin.");
                        break;

                    case 2: // Hapus Barang
                        if (roleAktif.equals("Admin")) {
                            System.out.print("Masukkan Kode Barang yang dihapus: ");
                            int kdHapus = input.nextInt();
                            boolean found = daftarBarang.removeIf(b -> b.getKode_barang() == kdHapus);
                            System.out.println(found ? "Berhasil dihapus." : "Kode tidak ditemukan.");
                        } else System.out.println("Akses Ditolak!");
                        break;

                    case 3: // Update Barang
                        if (roleAktif.equals("Admin")) {
                            System.out.print("Kode Barang yang diupdate: ");
                            int kdUpdate = input.nextInt();
                            for (Barang b : daftarBarang) {
                                if (b.getKode_barang() == kdUpdate) {
                                    System.out.print("Nama Baru: "); b.setNama_barang(input.next());
                                    System.out.print("Harga Baru: "); b.setHarga(input.nextInt());
                                    System.out.print("Stok Baru: "); b.setStok(input.nextInt());
                                    System.out.println("Update Berhasil.");
                                }
                            }
                        } else System.out.println("Akses Ditolak!");
                        break;

                    case 4: // Keuntungan
                        if (roleAktif.equals("Admin")) {
                            double totalSemua = 0;
                            System.out.println("\n-- LAPORAN PENJUALAN PER BARANG --");
                            for (Barang b : daftarBarang) {
                                double untungBarang = 0; int terjual = 0;
                                for (Transaksi t : daftarTransaksi) {
                                    // Melakukan perulangan untuk menghitung total penjualan dan keuntungan per barang
                                    for (ItemTransaksi it : t.items) {
                                        if (it.getBarang().getKode_barang() == b.getKode_barang()) {
                                            terjual += it.getJumlah();
                                            untungBarang += it.getSubtotal();
                                        }
                                    }
                                }
                                // Jika barang tersebut terjual, tampilkan informasi penjualan
                                if (terjual > 0) {
                                    System.out.println("Barang: " + b.getNama_barang() + " | Laku: " + terjual + " | Total: Rp " + untungBarang);
                                    totalSemua += untungBarang;
                                }
                            }
                            System.out.println("----------------------------------");
                            System.out.println("TOTAL PENDAPATAN TOKO: Rp " + totalSemua);
                        } else System.out.println("Akses Ditolak!");
                        break;

                    case 5: // Tampilkan Barang
                        System.out.println("\n--- STOK BARANG SAAT INI ---");
                        if (daftarBarang.isEmpty()) System.out.println("Gudang Kosong.");
                        else {
                            // Melakukan perulanagan untuk menampilkan semua barang
                            for (Barang b : daftarBarang) b.tampilkanBarang();
                        }
                        break;

                    case 6: // Tambah Pembeli
                        if (roleAktif.equals("Kasir")) {
                            System.out.print("ID Pembeli: "); int idp = input.nextInt();
                            System.out.print("Nama: "); String np = input.next();
                            System.out.print("Alamat: "); String ap = input.next();
                            daftarPembeli.add(new Pembeli(idp, np, ap));
                            System.out.println("Data pembeli disimpan.");
                        } else System.out.println("Akses Ditolak!");
                        break;

                    case 7: // Transaksi Baru (Multiple Items)
                        if (roleAktif.equals("Kasir")) {
                            if (daftarBarang.isEmpty() || daftarPembeli.isEmpty()) {
                                System.out.println("Data Barang/Pembeli belum ada!"); break;
                            }
                            // Pilih Pembeli
                            System.out.println("Daftar Pembeli:");
                            for (int i = 0; i < daftarPembeli.size(); i++)
                                System.out.println(i + ". " + daftarPembeli.get(i).getNama());
                            System.out.print("Pilih Index: ");
                            Pembeli pTerpilih = daftarPembeli.get(input.nextInt());
                            
                            // Apakah Pembeli Member atau Umum
                            System.out.print("Apakah Member? (y/n): ");
                            boolean member = input.next().equalsIgnoreCase("y");

                            // Membuat nota baru
                            Transaksi transaksiBaru = new Transaksi(pTerpilih, member);
                            
                            // Menambahkan item ke dalam Transaksi
                            boolean tambahLagi = true;
                            while (tambahLagi) {
                                System.out.print("Masukkan Kode Barang: ");
                                int kdBeli = input.nextInt();
                                Barang bKetemu = null;
                                for (Barang b : daftarBarang) if (b.getKode_barang() == kdBeli) bKetemu = b;

                                // Jika barang ditemukan, tanyakan jumlah yang ingin dibeli dan tambahkan ke Transaksi
                                if (bKetemu != null) {
                                    System.out.print("Jumlah: "); int jml = input.nextInt();
                                    if (bKetemu.getStok() >= jml) {
                                        // Tambahkan item ke dalam Transaksi
                                        transaksiBaru.tambahItem(bKetemu, jml);
                                        bKetemu.kurangiStok(jml);
                                        System.out.println("Item ditambahkan.");
                                    } else System.out.println("Stok Kurang!");
                                } else System.out.println("Barang tidak ditemukan!");

                                System.out.print("Tambah item lain ke nota ini? (y/n): ");
                                tambahLagi = input.next().equalsIgnoreCase("y");
                            }
                            // Simpan Transaksi ke Riwayat
                            daftarTransaksi.add(transaksiBaru);
                            transaksiBaru.cetakNota();
                        } else System.out.println("Akses Ditolak!");
                        break;

                    case 8: // Riwayat Transaksi
                        System.out.println("\n-- RIWAYAT SEMUA NOTA --");
                        if (daftarTransaksi.isEmpty()) System.out.println("Belum ada transaksi.");
                        else {
                            // Melakukan perulanagan untuk menampilkan semua transaksi yang sudah dilakukan
                            for (Transaksi t : daftarTransaksi) t.cetakNota();
                        }
                        break;

                    case 9: // Logout
                        // Keluar dari menu dan kembali ke pemilihan role
                        isLoggedIn = false;
                        System.out.println("Kembali ke Menu Utama.");
                        break;
                }
            }
        }
        input.close();
    }
}