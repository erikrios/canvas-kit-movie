package io.erikrios.github.canvaskitmovie.utils

object ImageConfigurations {

    private const val SECURE_BASE_URL = "https://image.tmdb.org/t/p/"

    enum class ImageType { BACKDROP, POSTER, PROFILE }

    enum class BackdropSize(val size: String) {
        WIDTH_300("w300"),
        WIDTH_780("w780"),
        WIDTH_1280("w1280"),
        ORIGINAL("original")
    }

    enum class PosterSize(val size: String) {
        WIDTH_92("w92"),
        WIDTH_154("w154"),
        WIDTH_185("w185"),
        WIDTH_342("w342"),
        WIDTH_500("w500"),
        WIDTH_700("w780"),
        ORIGINAL("original")
    }

    enum class ProfileSize(val size: String) {
        WIDTH_45("w45"),
        WIDTH_185("w185"),
        HEIGHT_632("h632"),
        ORIGINAL("original")
    }

    fun generateFullImageUrl(
        path: String,
        imageType: ImageType,
        backdropSize: BackdropSize = BackdropSize.ORIGINAL,
        posterSize: PosterSize = PosterSize.ORIGINAL,
        profileSize: ProfileSize = ProfileSize.ORIGINAL
    ): String {
        var fullImageUrl = SECURE_BASE_URL

        fullImageUrl = when (imageType) {
            ImageType.BACKDROP -> fullImageUrl.plus(backdropSize.size)
            ImageType.POSTER -> fullImageUrl.plus(posterSize.size)
            ImageType.PROFILE -> fullImageUrl.plus(profileSize.size)
        }

        return fullImageUrl.plus(path)
    }
}