# NewsApp - Android News Reader (Week 6)

Aplikasi pembaca berita sederhana yang dibangun menggunakan bahasa Kotlin dengan menerapkan **Repository Pattern** dan **Architecture Component (ViewModel & LiveData)**. Proyek ini merupakan bagian dari tugas praktikum minggu ke-6.

## ✨ Fitur Utama
* **Fetch API**: Mengambil data berita terkini secara real-time dari [NewsAPI.org](https://newsapi.org).
* **Repository Pattern**: Abstraksi sumber data untuk memisahkan logika bisnis dengan akses data API.
* **State Management**: Menangani kondisi **Loading** (ProgressBar), **Success** (Menampilkan list), dan **Error** (Toast/Pesan error saat internet mati).
* **Pull to Refresh**: Mengupdate berita terbaru dengan menarik layar ke bawah menggunakan `SwipeRefreshLayout`.
* **Modern UI**: Menggunakan `RecyclerView` dengan `CardView` untuk tampilan list yang bersih dan modern.
* **Image Loading**: Menggunakan library **Coil** untuk memuat gambar berita secara asinkron.

## 🛠️ Tech Stack & Library
* **Kotlin**: Bahasa pemrograman utama.
* **Retrofit & OkHttp**: Untuk networking dan koneksi ke REST API.
* **ViewModel & LiveData**: Untuk menjaga data tetap ada saat rotasi layar dan sinkronisasi data ke UI.
* **Coroutines**: Untuk menangani proses asinkron (background task).
* **Coil**: Library image loading yang ringan.

## 📸 Tampilan Aplikasi
*(Opsional: Masukkan screenshot aplikasi kamu di sini)*
![Screenshot List Berita](https://via.placeholder.com/300x600?text=Screenshot+News+App)

## 🚀 Cara Menjalankan
1. Clone repository ini:
   ```bash
   git clone -b week-6 [https://github.com/USERNAME_KAMU/NewsApp.git](https://github.com/USERNAME_KAMU/NewsApp.git)
