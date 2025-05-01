package com.example.sdcard

import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etCgpa: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etCgpa = findViewById(R.id.etCgpa)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val cgpa = etCgpa.text.toString()

            if (name.isNotEmpty() && cgpa.isNotEmpty()) {
                saveToFile(name, cgpa)
            } else {
                Toast.makeText(this, "Please enter both Name and CGPA", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveToFile(name: String, cgpa: String) {
        val fileName = "student_data.txt"
        val fileContents = "Name: $name\nCGPA: $cgpa"

        // Save in app-specific external storage
        val file = File(getExternalFilesDir(null), fileName)

        try {
            FileOutputStream(file).use { it.write(fileContents.toByteArray()) }
            Toast.makeText(this, "Saved to ${file.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}