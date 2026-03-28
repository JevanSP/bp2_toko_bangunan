import java.util.Scanner;
import java.util.ArrayList; // Import ArrayList 

public class MainApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Menggunakan ArrayList untuk menyimpan banyak objek
        ArrayList<Barang> daftarBarang = new ArrayList<>();
        ArrayList<Pembeli> daftarPembeli = new ArrayList<>();
        ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();

        // Membuat daftar kategori dengan menggunakan ArrayList untuk menyimpan banyak kategori
        ArrayList<Kategori> daftarKategori = new ArrayList<>();
        daftarKategori.add(new Kategori("Cat"));
        daftarKategori.add(new Kategori("Pipa"));
        daftarKategori.add(new Kategori("Semen"));
        daftarKategori.add(new Kategori("Bata"));
        daftarKategori.add(new Kategori("Tekiro"));
        int pilihan_user, pilihan;
        String role = "";
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== SISTEM POS TOKO BANGUNAN ===");
            System.out.println("1. Admin");
            System.out.println("2. Kasir");
            System.out.println("3. Keluar");
            System.out.print("Pilih role: ");
            pilihan_user = input.nextInt();
            input.nextLine();

            if (pilihan_user == 1)
                role = "Admin";
            else if (pilihan_user == 2)
                role = "Kasir";
            else if (pilihan_user == 3)
                break;
            else
                continue;

            boolean isLoggedIn = true;
            while (isLoggedIn) {
                System.out.println("\n-- Menu " + role + " --");
                System.out.println("1. Tambah Barang (Admin)");
                System.out.println("2. Hapus Barang (Admin)");
                System.out.println("3. Update Barang (Admin)");
                System.out.println("4. Tampilkan Semua Barang");
                System.out.println("5. Tambah Pembeli (Kasir)");
                System.out.println("6. Transaksi Baru (Kasir)");
                System.out.println("7. Riwayat Transaksi");
                System.out.println("8. Log Out");
                System.out.print("Pilihan: ");
                pilihan = input.nextInt();
                input.nextLine();

                switch (pilihan) {
                    case 1: // Tambah Barang
                        if (role.equals("Admin")) {
                            System.out.print("Kode: ");
                            int kd = input.nextInt();
                            System.out.print("Nama: ");
                            String nm = input.next();
                            System.out.println("Pilih Kategori:");
                            for (int i = 0; i < daftarKategori.size(); i++) {
                                System.out.println(i + ". " + daftarKategori.get(i).getNamaKategori());
                            }
                            System.out.print("Masukkan index kategori: ");
                            int pilKat = input.nextInt();
                            System.out.print("Harga: ");
                            int hg = input.nextInt();
                            System.out.print("Stok: ");
                            int st = input.nextInt();
                            Kategori katTerpilih = daftarKategori.get(pilKat);
                            daftarBarang.add(new Barang(kd, nm, hg, st, katTerpilih));
                            System.out.println("Barang dengan kategori " + katTerpilih.getNamaKategori() + " berhasil disimpan.");
                        } else {
                            System.out.println("Akses Ditolak!");
                        }
                        break;

                    case 2: // Hapus Barang
                        System.out.println("Masukkan Kode Barang yang ingin dihapus: ");
                        int kdHapus = input.nextInt();
                        // Mencari barang dengan kode yang diinputkan untuk dihapus
                        boolean found = false;
                        for (int i = 0; i < daftarBarang.size(); i++) {
                            if (daftarBarang.get(i).getKode_barang() == kdHapus) {
                                daftarBarang.remove(i);
                                System.out.println("Barang dengan kode " + kdHapus + " berhasil dihapus.");
                                found = true;
                                break;
                            }
                        }
                        // jika kode barang tidak ditemukan dalam daftar
                        if (!found)
                            System.out.println("Barang dengan kode " + kdHapus + " tidak ditemukan.");
                        break;

                    case 3: // Update Barang
                        System.out.println("Masukkan Kode Barang yang ingin diupdate: ");
                        int kdUpdate = input.nextInt();
                        // Mencari barang dengan kode yang diinputkan untuk diupdate
                        boolean foundUpdate = false;
                        for (int i = 0; i < daftarBarang.size(); i++) {
                            if (daftarBarang.get(i).getKode_barang() == kdUpdate) {
                                System.out.print("Nama Baru: ");
                                String nmBaru = input.next();
                                System.out.print("Harga Baru: ");
                                int hgBaru = input.nextInt();
                                System.out.print("Stok Baru: ");
                                int stBaru = input.nextInt();
                                daftarBarang.get(i).setNama_barang(nmBaru);
                                daftarBarang.get(i).setHarga(hgBaru);
                                daftarBarang.get(i).setStok(stBaru);
                                System.out.println("Barang dengan kode " + kdUpdate + " berhasil diupdate.");
                                foundUpdate = true;
                            }
                            else {
                                System.out.println("Barang dengan kode " + kdUpdate + " tidak ditemukan.");
                            }
                            break;
                        }

                    case 4: // Tampilkan Barang
                        System.out.println("--- Daftar Barang ---");
                        if (daftarBarang.isEmpty())
                            System.out.println("Kosong.");
                        else {
                            // Melakukan perulanagan untuk menampilkan semua barang
                            for (Barang b : daftarBarang)
                                b.tampilkanBarang();
                        }
                        break;

                    case 5: // Tambah Pembeli
                        if (role.equals("Kasir")) {
                            System.out.print("ID Pembeli: ");
                            int idp = input.nextInt();
                            System.out.print("Nama: ");
                            String np = input.next();
                            System.out.print("Alamat: ");
                            String ap = input.next();
                            daftarPembeli.add(new Pembeli(idp, np, ap));
                        } else
                            System.out.println("Akses Ditolak!");
                        break;

                    case 6: // Transaksi
                        if (role.equals("Kasir")) {
                            if (daftarBarang.isEmpty() || daftarPembeli.isEmpty()) {
                                System.out.println("Data barang/pembeli belum ada!");
                                break;
                            }
                            // Mencari barang dengan menginput index barang
                            System.out.println("Masukkan kode barang yang ingin dibeli: ");
                            int kdBeli = input.nextInt();
                            int idxB = -1;
                            for (int i = 0; i < daftarBarang.size(); i++) {
                                if (daftarBarang.get(i).getKode_barang() == kdBeli) {
                                    idxB = i;
                                    break;
                                }
                            }

                            // Mencari pembeli dengan menginput index pembeli
                            System.out.println("Pilih Index Pembeli (0-" + (daftarPembeli.size() - 1) + "): ");
                            int idxP = input.nextInt();
                            // Mengnputkan jumlah barang
                            System.out.print("Jumlah: ");
                            int jml = input.nextInt();

                            Barang bTerpilih = daftarBarang.get(idxB);
                            if (bTerpilih.getStok() >= jml) {
                                Transaksi t = new Transaksi();
                                // Menambah transaksi barang terpilih akan menghitung jumlah yang dibeli dan
                                // stok akan dikurangi
                                t.TambahTransaksi(bTerpilih, daftarPembeli.get(idxP), jml);
                                bTerpilih.kurangiStok(jml);
                                daftarTransaksi.add(t);
                                t.cetakTransaksi();
                            } else
                                System.out.println("Stok Kurang!");
                        } else
                            System.out.println("Akses Ditolak!");
                        break;

                    case 7: // Riwayat Transaksi
                        System.out.println("--- Riwayat Transaksi ---");
                        // Melakukan perulanagan untuk menampilkan semua transaksi yang sudah dilakukan
                        for (Transaksi t : daftarTransaksi)
                            t.cetakTransaksi();
                        break;

                    case 8:// log out
                           // Keluar dari menu dan kembali ke pemilihan role
                        isLoggedIn = false;
                        break;
                }
            }
        }
    }
}
