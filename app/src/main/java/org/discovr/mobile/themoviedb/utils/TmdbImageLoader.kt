
import org.discovr.mobile.core.domain.Path
import org.discovr.mobile.core.utils.ImageLoader
import java.net.URL

class TheMovieDBImageLoader(override val baseUrl: URL) : ImageLoader {

    override fun loadSmall(path: Path?): String {
        return path?.let {
            URL(baseUrl.toString() + "w200" + path.value).toString()
        } ?: ""
    }

    override fun loadMedium(path: Path?): String {
        return path?.let {
            URL(baseUrl.toString() + "w400" + path.value).toString()
        } ?: ""
    }

    override fun loadLarge(path: Path?): String {
        return path?.let {
            URL(baseUrl.toString() + "w500" + path.value).toString()
        } ?: ""
    }

    override fun loadOriginal(path: Path?): String {
        return path?.let {
            URL(baseUrl.toString() + "original" + path.value).toString()
        } ?: ""
    }
}