# ImageCache
缓存图片


加载图片时，会先从内存中读取，如果没有读取到，然后再从sd卡中读取图片，如果从sd卡也没有读取到图片，那么才会从网络下载图片，然后再缓存图片到内存及sd卡。