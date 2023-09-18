package br.com.fiap.startupfiap.tools

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.app.ActivityCompat
import br.com.fiap.startupfiap.screens.copyAssetFileToStorage
import br.com.fiap.startupfiap.screens.copyFile
import br.com.fiap.startupfiap.screens.getAllAssetFileNames
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


@OptIn(ExperimentalMaterial3Api::class)
fun revertDrawerState(scope: CoroutineScope, drawerState: DrawerState){
    if (drawerState.isClosed){
        scope.launch { drawerState.open() }
    } else {
        scope.launch { drawerState.close() }
    }
}

fun saveSampleFilesToExternalStorage(context: Context) {
    val activity = context as Activity
    ActivityCompat.requestPermissions(
        activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 23
    )
    val downloadsFolder = Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_DOWNLOADS
    )
    val appFolder: File = File(downloadsFolder, "StartupFIAP")
    if (!appFolder.exists())
        appFolder.mkdirs()

    val filesToSave = getAllAssetFileNames(context)

    for(file in filesToSave){
        val destinyFile = File(appFolder, file)
        if(destinyFile.isFile and destinyFile.exists()){
            Log.d("StartupFIAPLog", "Sample file exists already and will not be overwritten to $destinyFile")
        } else {
            val copiedFile = copyAssetFileToStorage(context, file)
            if (copiedFile != null) {
                Log.d("StartupFIAPLog", "Sample file will be saved to ${copiedFile.absolutePath}")
                copyFile(copiedFile, destinyFile)
            } else {
                Log.d("StartupFIAPLog", "Error copying file")
            }
        }
    }
}

fun getAllAssetFileNames(context: Context): List<String> {
    val assetManager = context.assets
    val assetFileNames = mutableListOf<String>()
    try {
        val assetFiles = assetManager.list("sampledata") ?: arrayOf()
        for (assetFileName in assetFiles) {
            assetFileNames.add(assetFileName)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return assetFileNames
}


fun copyAssetFileToStorage(context: Context, assetFileName: String): File? {
    try {
        val targetFile = File(context.filesDir, assetFileName)
        val assetManager = context.assets
        val inputStream = assetManager.open("sampledata/$assetFileName")
        val outputStream = FileOutputStream(targetFile)

        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) {
            outputStream.write(buffer, 0, length)
        }
        inputStream.close()
        outputStream.close()
        return targetFile
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }
}


private fun writeBytesToFile(file: File, data: ByteArray) {
    var fileOutputStream: FileOutputStream? = null
    try {
        fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(data)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}

fun copyFile(sourceFile: File, destinationFile: File) {
    var inputStream: FileInputStream? = null
    try {
        inputStream = FileInputStream(sourceFile)
        val data = inputStream.readBytes()
        writeBytesToFile(destinationFile, data)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        if (inputStream != null) {
            try {
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}


fun printSampleFileContentToLogs(context: Context, fileName: String) {
    try {
        val inputStream = context.assets.open("sampledata/${fileName}")
        val buffer = ByteArray(1024)
        val stringBuilder = StringBuilder()
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) {
            val text = String(buffer, 0, length)
            stringBuilder.append(text)
        }
        inputStream.close()
        val fileContent = stringBuilder.toString()
        Log.d("FileContent", "File content: $fileContent")
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun scanSystemForFiles(directoryPath: String): Map<String, List<String>> {
    val fileHashMap = mutableMapOf<String, MutableList<String>>()
    val directory = File(directoryPath)

    if (directory.exists() && directory.isDirectory) {
        scanDirectory(directory, fileHashMap)
    }

    return fileHashMap
}

private fun scanDirectory(directory: File, fileHashMap: MutableMap<String, MutableList<String>>) {
    val files = directory.listFiles()
    files?.forEach { file ->
        if (file.isFile) {
            val extension = file.extension.toLowerCase()
            val fileName = file.name

            if (!fileHashMap.containsKey(extension)) {
                fileHashMap[extension] = mutableListOf(fileName)
            } else {
                val existingFiles = fileHashMap[extension] as MutableList<String>
                existingFiles.add(fileName)
            }
        } else if (file.isDirectory) {
            // Recursively scan subdirectories
            scanDirectory(file, fileHashMap)
        }
    }
}
