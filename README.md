# MagicFunction

Инструкция была придумана не для русских, поэтому нахуй ее.
Давай лучше документировать код, **а точнее выписать довольно интересные моменты.**

Проблемы перед разработкой приложения
- [x] Подключится к следующим API:
1. https://aws.random.cat/meow - выкидывает рандомные картинки котиков.
2. http://www.boredapi.com/api/activity/ - выкидывает рандомное действие.
3. https://http.cat/ - выкидывает картинку с интернет статусом, который приписывается к концу ссылки.


- [x] Скачивание картинок.
Реализованно следующим образом.

```
object DownloadImage {
    fun execute(url: String, context: Context) {
        val executor = Executors.newSingleThreadExecutor()
        val handler = android.os.Handler(Looper.getMainLooper())
        var image: Bitmap?

        executor.execute {
            image = mLoad(url = url, context = context)
            handler.post {
                mSaveMediaToStorage(image, context = context)
            }
        }
    }

    private fun mLoad(url: String, context: Context): Bitmap? {
        val _url: URL = mStringToURL(url)!!
        val connection: HttpURLConnection?

        try {
            connection = _url.openConnection() as HttpURLConnection
            connection.connect()

            val inputStream: InputStream = connection.inputStream
            val bufferedInputStream = BufferedInputStream(inputStream)
            return BitmapFactory.decodeStream(bufferedInputStream)
        } catch (e: IOException){
            e.printStackTrace()
            Log.e("downloadImage", "error ", )
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
        }

        return null
    }

    private fun mStringToURL(string: String): URL? {
        try {
            return URL(string)
        } catch (e: MalformedURLException){
            e.printStackTrace()

        }

        return null
    }

    private fun mSaveMediaToStorage(bitmap: Bitmap?, context: Context) {
        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }
        fos?.use {
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(context , "Saved to Gallery" , Toast.LENGTH_SHORT).show()
        }
    }
}
```


- [x] Детект анимации были реализованны следующим образом

```
var visibleAnimation by remember { mutableStateOf(false) } // переменная для старта начала анимации
AnimatedVisibility(
            visible = visibleAnimation,
            enter = ...,
            exit = ...,
            modifier = ...
) {
	// content...
}


LaunchedEffect(
        key1 = Unit,
        block = {
            delay(300)
            visibleAnimation = true // просто запустит анимацию без каких либо понтов.
        }
)


```

- [x] Анимации Lottie

```
val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = Int.MAX_VALUE
    )

LottieAnimation(
            composition = composition,
            progress = progress,
            alignment = ...,
            modifier = ...
)
```


- [x] Проверка, имеется ли интеренет соединение.

```
fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}
```
---------------------------------------------------------------------------------------------------------

## UseCases Application
**Я не смогу правильно изначально реализовать UseCases, поэтому только тут напишу(мб пофикшу)**
```
 1. SwipeNavigation.execute(navController: NavController): Unit
 
 2. GetRandomCat.execute(): String
 
 3. GetInternetStatusImages.execute(): List<String>
 
 4. GetActivities.execute(): List<ActivityPOJO>
 
 5. ShowHistory.execute(): List<LikeActivitiesEntity>
 ```
 Надеюсь смог правильно указать типы.


---------------------------------------------------------------------------------------------------------


## Проблемы которые следует решить
- [ ] Научиться пользоватся Dagger Hilt.
- [ ] Научится правильно детектить изменение списка и во время сообщать фреймворку, что пора рекомпозироваться.
- [ ] Научится делать UI елементы адаптивными для любых устройств.
- [ ] Научиться использовать MaterialTheme.
- [ ] Научиться делать приложения и для горизонтальной ориентации.
- [ ] Научиться граммотно использовать CustomFontFamily (чтоб можно было нормально условно Толщину настраивать).





~~Начну делать пожалуй рефакторин~~
