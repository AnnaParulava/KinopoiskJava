package ru.poly.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
internal fun MovieList(
    movieState: MovieState.Success,
    modifier: Modifier = Modifier
) {
    MovieList(movies = movieState.movies, modifier)
}

@Preview
@Composable
internal fun MovieList(
    @PreviewParameter(MoviesPreviewProvider::class, limit = 1) movies: List<MovieUI>,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier.padding(horizontal = 8.dp)) {
        items(movies) { movie ->
            key(movie.id) {
                MovieItem(movie)
            }
        }
    }
}

@Preview
@Composable
internal fun MovieItem(
    @PreviewParameter(MoviePreviewProvider::class, limit = 1) movie: MovieUI,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(150.dp)
            .padding(8.dp)
    ) {
        movie.posterUrl?.let { imageUrl ->
            AsyncImage(
                model = imageUrl,
                contentDescription = stringResource(R.string.content_desc_item_movie_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

private class MoviesPreviewProvider : PreviewParameterProvider<List<MovieUI>> {
    private val movieProvider = MoviePreviewProvider()

    override val values =
        sequenceOf(
            movieProvider.values.toList()
        )
}

@Suppress("MagicNumber")
private class MoviePreviewProvider : PreviewParameterProvider<MovieUI> {
    override val values =
        sequenceOf(
            MovieUI(1, "Интерстеллар", "https://image.tmdb.org/t/p/w500/abc.jpg"),
            MovieUI(2, "Начало", "https://image.tmdb.org/t/p/w500/xyz.jpg"),
            MovieUI(3, "Дюна", "https://image.tmdb.org/t/p/w500/123.jpg")
        )
}
