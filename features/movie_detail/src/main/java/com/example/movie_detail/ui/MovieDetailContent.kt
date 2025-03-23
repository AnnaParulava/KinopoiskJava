package com.example.movie_detail.ui

//@Composable
//fun MovieDetailContent(movie: MovieDetail, navController: NavController) {
//    Column(modifier = Modifier.fillMaxSize()) {
//        // Верхний бар со стрелкой назад
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            IconButton(onClick = { navController.popBackStack() }) {
//                Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
//            }
//            Spacer(Modifier.width(8.dp))
//            Text(movie.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
//        }
//
//        // Основной постер
//        AsyncImage(
//            model = movie.posterUrl,
//            contentDescription = movie.title,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp)
//        )
//
//        // Описание
//        Text(
//            text = movie.description ?: "Описание отсутствует",
//            fontSize = 16.sp,
//            modifier = Modifier.padding(16.dp)
//        )
//
//        // Список постеров
//        Text(
//            text = "Галерея",
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(horizontal = 16.dp)
//        )
//
//        LazyRow(
//            contentPadding = PaddingValues(horizontal = 16.dp),
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            items(movie.galleryPosters) { posterUrl ->
//                AsyncImage(
//                    model = posterUrl,
//                    contentDescription = "Постер",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .width(150.dp)
//                        .height(200.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                )
//            }
//        }
//    }
//}
