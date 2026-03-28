class Barang {
    private int kode_barang;
    private String nama_barang;
    private int harga;
    private int stok;
    private Kategori kategori;

    public Barang(int kd, String nm, int hg, int st, Kategori kt) {
        kode_barang = kd;
        nama_barang = nm;
        kategori = kt;
        harga = hg;
        stok = st;
    }
    // getter untuk menampilkan informasi barang
    public int getKode_barang() {
        return kode_barang;
    }
    public String getNama_barang() {
        return nama_barang;
    }
    public int getHarga() {
        return harga;
    }
    public int getStok() {
        return stok;
    }
    // setter untuk update barang
    public void setKode_barang(int kode_barang) {
        this.kode_barang = kode_barang;
    }
    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }
    public void setHarga(int harga) {
        this.harga = harga;
    }
    public void setStok(int stok) {
        this.stok = stok;
    }
    // method untuk menampilkan informasi barang
    public void tampilkanBarang() {
        System.out.println("Kode Barang: " + kode_barang);
        System.out.println("Nama Barang: " + nama_barang);
        System.out.println("Kategori: " + kategori.getNamaKategori());
        System.out.println("Harga: " + harga);
        System.out.println("Stok: " + stok);
    }
    // method untuk mengurangi stok barang setelah transaksi
    public void kurangiStok(int jumlah) {
        // validasi untuk memastikan stok tidak menjadi negatif
        if (jumlah <= stok) {
            stok -= jumlah;
            System.out.println("Stok barang " + nama_barang + " berhasil dikurangi. Stok sekarang: " + stok);
        } else {
            System.out.println("Stok barang " + nama_barang + " tidak cukup untuk transaksi.");
        }
    }
    // method untuk menambah stok barang
    public void tambahStok(int jumlah) {
        stok += jumlah;
        System.out.println("Stok barang " + nama_barang + " berhasil ditambah. Stok sekarang: " + stok);
    }
    
    // method untuk update barang
    public void updateBarang(int kd, String nm, int hg, int st) {
        kode_barang = kd;
        nama_barang = nm;
        harga = hg;
        stok = st;
        System.out.println("Barang berhasil diupdate.");
    }
}

