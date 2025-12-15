package com.example.herita.data.repository

data class QuizOption(
    val index: String, // A, B, C, D
    val content: String
)

data class QuizQuestion(
    val question: String,
    val option: List<QuizOption>
)

data class QuizReviewItem(
    val question: String,
    val explanation: String,
    val correctOption: QuizOption,
    val userAnswer: QuizOption
)

data class QuizReviewData(
    val score: Int,
    val maxScore: Int,
    val reviews: List<QuizReviewItem>
)

class QuizRepository {

    suspend fun getQuizReviews(): QuizReviewData {
        // Simulasi network delay
        kotlinx.coroutines.delay(1500)

        val reviews = listOf(
            QuizReviewItem(
                question = "Apa nama tarian tradisional dari Bali yang menceritakan kisah Ramayana?",
                explanation = "Tari Kecak adalah tarian tradisional Bali yang dimainkan oleh puluhan penari laki-laki yang duduk melingkar sambil menyerukan 'cak' dan mengangkat kedua tangan, menceritakan kisah Ramayana.",
                correctOption = QuizOption("A", "Tari Kecak"),
                userAnswer = QuizOption("B", "Tari Saman")
            ),
            QuizReviewItem(
                question = "Candi Borobudur terletak di provinsi mana?",
                explanation = "Candi Borobudur adalah candi Buddha terbesar di dunia yang terletak di Magelang, Jawa Tengah. Candi ini dibangun pada abad ke-8 dan merupakan salah satu warisan dunia UNESCO.",
                correctOption = QuizOption("C", "Jawa Tengah"),
                userAnswer = QuizOption("C", "Jawa Tengah")
            ),
            QuizReviewItem(
                question = "Alat musik tradisional Angklung berasal dari daerah?",
                explanation = "Angklung adalah alat musik multitonal yang terbuat dari bambu yang dimainkan dengan cara digoyangkan. Alat musik ini berasal dari Jawa Barat dan telah diakui UNESCO sebagai warisan budaya takbenda.",
                correctOption = QuizOption("B", "Jawa Barat"),
                userAnswer = QuizOption("A", "Jawa Timur")
            ),
            QuizReviewItem(
                question = "Rumah adat Tongkonan adalah rumah adat dari suku?",
                explanation = "Tongkonan adalah rumah adat suku Toraja di Sulawesi Selatan. Rumah ini memiliki atap yang melengkung seperti perahu dan merupakan pusat kehidupan sosial masyarakat Toraja.",
                correctOption = QuizOption("D", "Toraja"),
                userAnswer = QuizOption("D", "Toraja")
            ),
            QuizReviewItem(
                question = "Batik Tulis pertama kali diakui UNESCO sebagai warisan budaya pada tahun?",
                explanation = "Batik Indonesia diakui UNESCO sebagai Warisan Kemanusiaan untuk Budaya Lisan dan Nonbendawi pada tanggal 2 Oktober 2009. Tanggal ini kemudian diperingati sebagai Hari Batik Nasional.",
                correctOption = QuizOption("C", "2009"),
                userAnswer = QuizOption("B", "2008")
            ),
            QuizReviewItem(
                question = "Wayang Kulit adalah pertunjukan seni tradisional yang menggunakan?",
                explanation = "Wayang Kulit adalah seni pertunjukan tradisional Indonesia yang menggunakan boneka kulit yang diproyeksikan pada layar putih dengan bantuan lampu. Dalang berperan sebagai narator dan penggerak wayang.",
                correctOption = QuizOption("A", "Boneka dari kulit"),
                userAnswer = QuizOption("A", "Boneka dari kulit")
            ),
            QuizReviewItem(
                question = "Tari Saman berasal dari provinsi?",
                explanation = "Tari Saman adalah tarian tradisional dari suku Gayo di Aceh. Tarian ini biasanya ditampilkan untuk merayakan peristiwa penting dan telah diakui UNESCO sebagai warisan budaya takbenda pada tahun 2011.",
                correctOption = QuizOption("A", "Aceh"),
                userAnswer = QuizOption("C", "Sumatera Barat")
            ),
            QuizReviewItem(
                question = "Apa nama makanan tradisional Indonesia yang terbuat dari kedelai dan melalui proses fermentasi?",
                explanation = "Tempe adalah makanan tradisional Indonesia yang dibuat dari fermentasi kedelai menggunakan jamur Rhizopus. Tempe merupakan sumber protein nabati yang tinggi dan menjadi makanan pokok bagi banyak masyarakat Indonesia.",
                correctOption = QuizOption("B", "Tempe"),
                userAnswer = QuizOption("B", "Tempe")
            ),
            QuizReviewItem(
                question = "Reog Ponorogo adalah kesenian tradisional yang berasal dari?",
                explanation = "Reog Ponorogo adalah kesenian tradisional yang berasal dari Ponorogo, Jawa Timur. Kesenian ini menampilkan tarian dengan topeng kepala singa yang besar dan berat serta bulu merak.",
                correctOption = QuizOption("D", "Jawa Timur"),
                userAnswer = QuizOption("B", "Jawa Tengah")
            ),
            QuizReviewItem(
                question = "Gamelan adalah ansambel musik tradisional yang dominan berasal dari pulau?",
                explanation = "Gamelan adalah ensembel musik tradisional Indonesia yang dominan berasal dari Jawa, Bali, dan Sunda. Alat musik gamelan sebagian besar terdiri dari instrumen perkusi seperti gong, kendang, dan saron.",
                correctOption = QuizOption("A", "Jawa dan Bali"),
                userAnswer = QuizOption("A", "Jawa dan Bali")
            )
        )

        // Hitung score berdasarkan jawaban benar
        val correctAnswers = reviews.count { it.correctOption.index == it.userAnswer.index }
        val score = (correctAnswers * 10) // Misal setiap soal bernilai 10

        return QuizReviewData(
            score = score,
            maxScore = 100,
            reviews = reviews
        )
    }
}