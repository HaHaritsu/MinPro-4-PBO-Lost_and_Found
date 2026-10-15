# Minpro-4-PBO-LostnFound

「-------------------------
| Nama : Harits           |
| NIM  : 2509116048       |
-------------------------」

## Deskripsi Singkat Program

**LostnFound** merupakan aplikasi berbasis **CLI (Command Line Interface)** yang dibuat menggunakan bahasa pemrograman Java untuk membantu mengelola data barang hilang dan barang yang ditemukan.

Program ini merupakan pengembangan dari Mini Project sebelumnya dengan menerapkan konsep Pemrograman Berorientasi Objek (PBO), struktur MVC (*Model-View-Controller*), serta integrasi database MySQL menggunakan JDBC (*Java Database Connectivity*).

Fitur utama program meliputi pengelolaan data barang, pelapor, laporan kehilangan, dan temuan. Pengguna dapat menambah, melihat, mencari, menghapus data, serta mengubah status laporan dan temuan.

Penyimpanan data menggunakan **database MySQL**, sedangkan proses akses database dilakukan menggunakan JDBC, `PreparedStatement`, dan DAO (*Data Access Object*). Dengan demikian, data yang disimpan dapat tetap tersedia meskipun program ditutup dan dijalankan kembali.

Program juga menerapkan konsep PBO berupa **encapsulation, inheritance, polymorphism, abstraction**, serta interface sebagai nilai tambah.

---

## Teknologi yang Digunakan

- **Bahasa Pemrograman:** Java
- **IDE:** Apache NetBeans
- **Build Tool:** Maven
- **Database:** MySQL
- **Koneksi Database:** JDBC
- **Query Database:** `PreparedStatement`
- **Pola Akses Database:** DAO (*Data Access Object*)
- **Arsitektur Program:** MVC (*Model-View-Controller*)
- **Antarmuka:** CLI (*Command Line Interface*)

---

## Struktur Package

Struktur package pada program adalah sebagai berikut:

```text
lostnfound
├── pom.xml
├── lostnfound.sql
└── src/main/java
    ├── main
    │   └── Main.java
    │
    ├── config
    │   └── DatabaseConnection.java
    │
    ├── controller
    │   └── LnfController.java
    │
    ├── dao
    │   ├── BarangDAO.java
    │   ├── PelaporDAO.java
    │   ├── LaporanDAO.java
    │   └── TemuanDAO.java
    │
    ├── interfaces
    │   └── Identifiable.java
    │
    ├── model
    │   ├── Barang.java
    │   ├── DataStore.java
    │   ├── Pelapor.java
    │   ├── PelaporKehilangan.java
    │   ├── PelaporPenemu.java
    │   ├── Laporan.java
    │   └── Temuan.java
    │
    └── view
        └── LostFoundView.java
```

### Package `main`

Package `main` berisi `Main.java` yang menjadi titik awal program.

Tugas utamanya adalah:

- Membuat objek `LnfController`.
- Memeriksa koneksi database melalui `tesKoneksi()`.
- Menghentikan program jika koneksi database gagal.
- Membuat objek `LostFoundView`.
- Menjalankan program melalui method `mulaiProgram()`.

Program tidak lagi mengandalkan `DataStore` sebagai penyimpanan utama. Data dikelola melalui database MySQL.

### Package `config`

Package `config` berisi `DatabaseConnection.java`.

Class ini bertanggung jawab menyediakan koneksi ke database MySQL. Koneksi tersebut digunakan oleh class DAO untuk menjalankan operasi database.

### Package `controller`

Package `controller` berisi `LnfController.java`.

Controller menjadi penghubung antara View, Model, dan DAO. Tugasnya meliputi:

- Membuat ID otomatis untuk barang, pelapor, laporan, dan temuan.
- Memanggil DAO untuk menambah data.
- Mengambil dan mencari data dari database.
- Menghapus data.
- Mengubah status laporan dan temuan.
- Menangani kesalahan saat operasi database.

Dengan adanya controller, View tidak perlu menjalankan query SQL secara langsung.

### Package `dao`

Package `dao` berisi class yang menangani operasi database untuk setiap jenis data.

| Class | Tanggung Jawab |
|---|---|
| `BarangDAO.java` | Mengelola data barang |
| `PelaporDAO.java` | Mengelola data pelapor |
| `LaporanDAO.java` | Mengelola data laporan kehilangan |
| `TemuanDAO.java` | Mengelola data temuan |

DAO bertugas menjalankan operasi seperti menambah, membaca, mencari, mengubah, dan menghapus data menggunakan JDBC.

Penggunaan `PreparedStatement` membantu memisahkan query SQL dari nilai input pengguna dan mengurangi risiko SQL injection.

### Package `model`

Package `model` berisi class yang merepresentasikan objek dalam sistem.

- `Barang`
- `Pelapor`
- `PelaporKehilangan`
- `PelaporPenemu`
- `Laporan`
- `Temuan`
- `DataStore`

Class `Pelapor` menjadi superclass bagi `PelaporKehilangan` dan `PelaporPenemu`. Sementara itu, `Laporan` dan `Temuan` menghubungkan objek barang dengan pelapor yang sesuai.

`DataStore.java` masih terdapat dalam struktur proyek, tetapi penyimpanan utama pada versi ini dilakukan melalui MySQL menggunakan DAO.

### Package `view`

Package `view` berisi `LostFoundView.java`.

View bertanggung jawab terhadap interaksi pengguna melalui terminal, meliputi:

- Menampilkan menu utama dan submenu.
- Menerima input pengguna.
- Menampilkan data dari controller.
- Meminta konfirmasi sebelum penghapusan.
- Memvalidasi input wajib dan input angka.
- Menampilkan pesan hasil operasi.

### Package `interfaces`

Package `interfaces` berisi interface `Identifiable`.

```java
public interface Identifiable {
    String getId();
}
```

Interface tersebut menetapkan method `getId()` yang diimplementasikan oleh class `Barang`, `Pelapor`, `Laporan`, dan `Temuan`.

---

## Penjelasan Alur Program

### 1. Inisialisasi Program

Program dimulai dari `Main.java`. Pertama, program membuat objek controller dan memeriksa koneksi database.

```java
LnfController controller = new LnfController();

if (!controller.tesKoneksi()) {
    System.out.println(
        "Program dihentikan karena database tidak tersedia."
    );
    return;
}
```

Jika koneksi berhasil, program membuat objek View dan menjalankan menu utama.

```java
LostFoundView view = new LostFoundView(controller);
view.mulaiProgram();
```

Dengan pemeriksaan ini, program tidak melanjutkan operasi apabila database tidak dapat diakses.

### 2. Menu Utama

Setelah program dijalankan, pengguna akan mendapatkan menu utama:

```text
======================================
       SISTEM LOST AND FOUND
======================================
1. Kelola Barang
2. Kelola Pelapor
3. Kelola Laporan Kehilangan
4. Kelola Temuan
0. Keluar
======================================
```

Pengguna dapat memilih menu sesuai dengan data yang ingin dikelola. Menu akan terus ditampilkan melalui perulangan sampai pengguna memilih `0`.

### 3. Pengelolaan Barang

Menu Barang menyediakan fitur:

1. Tambah Barang
2. Lihat Barang
3. Cari Barang
4. Hapus Barang

Data barang terdiri dari ID, nama barang, kategori, warna, dan lokasi.

ID barang dibuat otomatis melalui method `generateIdBarang()`, dengan format seperti:

```text
B001
B002
B003
```

Ketika barang ditambahkan, controller meneruskan objek `Barang` kepada `BarangDAO` untuk disimpan ke database MySQL.

### 4. Pengelolaan Pelapor

Menu Pelapor menyediakan fitur:

1. Tambah Pelapor
2. Lihat Pelapor
3. Cari Pelapor
4. Hapus Pelapor

Pengguna dapat memilih salah satu jenis pelapor:

```text
1. Pelapor Kehilangan
2. Pelapor Penemu
```

Pelapor kehilangan memiliki data tambahan berupa NIK, sedangkan pelapor penemu memiliki data tambahan berupa lokasi temuan.

Kedua jenis pelapor memiliki ID masing-masing. Karena itu, pemilihan pelapor dilakukan berdasarkan ID, bukan hanya nama. Hal ini memungkinkan beberapa pelapor memiliki nama yang sama tanpa menyebabkan kekeliruan dalam pemilihan data.

Data pelapor disimpan dan diambil melalui `PelaporDAO`.

### 5. Pengelolaan Laporan Kehilangan

Menu Laporan Kehilangan menyediakan fitur:

1. Tambah Laporan
2. Lihat Laporan
3. Cari Laporan
4. Ubah Status Laporan
5. Hapus Laporan

Saat menambahkan laporan, program meminta ID pelapor, ID barang, tanggal kehilangan, lokasi kehilangan, dan keterangan.

Pelapor yang dipilih harus merupakan objek `PelaporKehilangan`. Controller melakukan pemeriksaan tipe pelapor sebelum meneruskan data ke `LaporanDAO`.

Status laporan dapat diubah melalui pilihan:

```text
1. Belum ditemukan
2. Ditemukan
3. Sudah dikembalikan
```

Perubahan status disimpan ke database melalui DAO sehingga perubahan tersebut dapat dipertahankan setelah program ditutup.

### 6. Pengelolaan Temuan

Menu Temuan menyediakan fitur:

1. Tambah Temuan
2. Lihat Temuan
3. Cari Temuan
4. Ubah Status Temuan
5. Hapus Temuan

Saat menambahkan temuan, program meminta ID barang dan meminta pengguna memilih pelapor penemu dari daftar yang tersedia.

Daftar penemu menampilkan ID, nama, nomor HP, dan lokasi temuan. Pemilihan menggunakan ID agar pelapor dengan nama yang sama tetap dapat dibedakan.

Setelah penemu dipilih, lokasi temuan diisi secara otomatis berdasarkan lokasi milik pelapor tersebut. Dengan demikian, pengguna tidak perlu memasukkan ulang lokasi yang sudah tersimpan pada data pelapor penemu.

Tanggal temuan divalidasi menggunakan format `yyyy-MM-dd`, misalnya `2026-10-15`.

Status temuan dapat diubah melalui pilihan:

```text
1. Belum diklaim
2. Sudah diklaim
3. Diserahkan
```

Data temuan dikelola melalui `TemuanDAO`.

### 7. Penyimpanan Database MySQL

Pada versi ini, data tidak lagi hanya bergantung pada `ArrayList` di dalam program. Data utama disimpan di database MySQL.

File `lostnfound.sql` digunakan untuk menyiapkan struktur database dan memasukkan data awal sesuai isi file SQL yang digunakan.

Proses pengelolaan data berlangsung melalui alur berikut:

```text
LostFoundView
      |
      v
LnfController
      |
      v
DAO
      |
      v
DatabaseConnection
      |
      v
MySQL
```

Ketika pengguna menambahkan atau mengubah data, controller memanggil DAO yang sesuai. DAO kemudian menjalankan query menggunakan JDBC dan `PreparedStatement`.

Saat data ditampilkan atau dicari, DAO mengambil data dari database dan mengembalikannya kepada controller untuk diteruskan ke View.

---

## Penerapan Konsep PBO

### 1. Encapsulation

Encapsulation diterapkan dengan menggunakan access modifier `private` pada atribut class. Atribut tersebut diakses melalui getter dan setter yang disediakan.

Contoh pada class `Barang`:

```java
private String idBarang;
private String namaBarang;
private String kategori;
private String warna;
private String lokasi;
```

Contoh getter dan setter:

```java
public String getNamaBarang() {
    return namaBarang;
}

public void setNamaBarang(String namaBarang) {
    this.namaBarang = namaBarang;
}
```

Penerapan encapsulation terdapat pada class model, seperti `Barang`, `Pelapor`, `PelaporKehilangan`, `PelaporPenemu`, `Laporan`, dan `Temuan`.

### 2. Inheritance

Inheritance diterapkan melalui superclass `Pelapor` dan dua subclass-nya.

```text
Pelapor
├── PelaporKehilangan
└── PelaporPenemu
```

Contoh deklarasi subclass:

```java
public class PelaporKehilangan extends Pelapor
```

```java
public class PelaporPenemu extends Pelapor
```

Kedua subclass mewarisi atribut dan method umum dari `Pelapor`, tetapi masing-masing memiliki data khusus. `PelaporKehilangan` menyimpan NIK, sedangkan `PelaporPenemu` menyimpan lokasi temuan.

### 3. Polymorphism

Polymorphism diterapkan melalui method overriding pada `getJenisPelapor()`.

Pada superclass `Pelapor`:

```java
public abstract String getJenisPelapor();
```

Pada subclass `PelaporKehilangan`:

```java
@Override
public String getJenisPelapor() {
    return "Pelapor Kehilangan";
}
```

Pada subclass `PelaporPenemu`:

```java
@Override
public String getJenisPelapor() {
    return "Pelapor Penemu";
}
```

Kedua subclass menggunakan nama method yang sama, tetapi memberikan hasil berbeda sesuai jenis objeknya.

### 4. Abstraction

Abstraction diterapkan dengan menjadikan `Pelapor` sebagai abstract class.

```java
public abstract class Pelapor implements Identifiable
```

Class tersebut mendefinisikan atribut dan perilaku umum pelapor tanpa menyediakan objek `Pelapor` secara langsung.

Abstract method `getJenisPelapor()` harus diimplementasikan oleh subclass, sehingga setiap jenis pelapor dapat memiliki perilaku yang sesuai.

---

## Penerapan Nilai Tambah

### 1. Interface

Interface `Identifiable` menetapkan method `getId()` bagi class yang mengimplementasikannya.

```java
public interface Identifiable {
    String getId();
}
```

Interface tersebut digunakan oleh `Barang`, `Pelapor`, `Laporan`, dan `Temuan`.

### 2. Constructor Overloading

Constructor overloading diterapkan dengan menyediakan beberapa constructor pada class yang sama dengan parameter berbeda.

Contohnya pada class `Barang`:

```java
public Barang(String idBarang, String namaBarang, String kategori,
              String warna, String lokasi)
```

dan:

```java
public Barang(String idBarang, String namaBarang, String kategori)
```

Constructor kedua memanggil constructor pertama dan memberikan nilai default untuk atribut warna serta lokasi.

Konsep ini juga terdapat pada `PelaporKehilangan`, `PelaporPenemu`, `Laporan`, dan `Temuan`.

### 3. Method Overloading

Method overloading diterapkan pada `LnfController` melalui dua method `cariBarang()` dengan parameter berbeda.

```java
public Barang cariBarang(String id)
```

```java
public Barang cariBarang(String nama, String kategori)
```

Method pertama mencari barang berdasarkan ID, sedangkan method kedua mencari barang berdasarkan nama dan kategori.

### 4. MVC (Model-View-Controller)

Struktur MVC memisahkan tanggung jawab program menjadi beberapa bagian.

- **Model:** Merepresentasikan objek dan data program.
- **View:** Menampilkan menu dan menerima input pengguna.
- **Controller:** Mengatur alur pengolahan data dan menghubungkan View dengan DAO.

Class `Main` menjalankan program dan menginisialisasi controller serta view.

### 5. JDBC dan PreparedStatement

JDBC digunakan untuk menghubungkan program Java dengan database MySQL. `PreparedStatement` digunakan untuk menjalankan query SQL dengan parameter.

Penerapannya berada pada class DAO yang menangani operasi database.

Penggunaan mekanisme tersebut membantu memisahkan proses penyimpanan data dari tampilan program serta membuat query lebih terstruktur.

### 6. DAO (Data Access Object)

Setiap jenis data memiliki DAO masing-masing, yaitu `BarangDAO`, `PelaporDAO`, `LaporanDAO`, dan `TemuanDAO`.

Pemisahan ini membuat proses akses database tidak menumpuk di dalam controller atau view.

---

## Kesimpulan

**LostnFound Mini Project 4** merupakan pengembangan aplikasi CLI pengelolaan barang hilang dan barang temuan dengan integrasi database MySQL menggunakan JDBC. Program menyediakan fitur pengelolaan barang, pelapor, laporan kehilangan, dan temuan, serta menggunakan `PreparedStatement` dan DAO untuk menjalankan operasi database.

Selain integrasi database, program tetap menerapkan konsep PBO berupa encapsulation, inheritance, polymorphism, abstraction, interface, constructor overloading, dan method overloading. Penggunaan MVC dan DAO membantu memisahkan tanggung jawab setiap class sehingga struktur program lebih terorganisir dan lebih mudah dikembangkan.
