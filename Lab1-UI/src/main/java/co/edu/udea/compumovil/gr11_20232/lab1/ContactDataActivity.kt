package co.edu.udea.compumovil.gr11_20232.lab1
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity


class ContactDataActivity: ComponentActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myIntent = intent

        val personalData = myIntent.getStringExtra("personalData")

        setContentView(R.layout.activity_contact_data)

        //Ícono y texto telefono
        val telefono: ImageView = findViewById(R.id.telefonoImageView)
        telefono.setImageResource(R.drawable.telefono)

        val textoTelefono = findViewById<EditText>(R.id.telefonoEditText)
        textoTelefono.setOnClickListener{
            textoTelefono.requestFocus()
        }


        //Ícono y texto correo
        val correoIcono: ImageView = findViewById(R.id.correoImageView)
        correoIcono.setImageResource(R.drawable.mail)

        val textoCorreo = findViewById<EditText>(R.id.correoEditText)
        textoCorreo.setOnClickListener{
            textoCorreo.requestFocus()
        }

        //Íconoy texto ciudades
        val ciudadIcono: ImageView = findViewById(R.id.ciudadImageView)
        ciudadIcono.setImageResource(R.drawable.city)

        val ciudades = arrayOf(
            "Bogotá", "Medellín", "Cali", "Barranquilla", "Cartagena de Indias", "Cúcuta",
            "Soledad", "Ibagué", "Bucaramanga", "Soacha", "Santa Marta", "Villavicencio",
            "Bello ", "Pereira", "Valledupar", "Manizales", "Buenaventura",
            "Pasto ", "Montería " ,"Neiva", "Armenia")

        val textoCiudad = findViewById<AutoCompleteTextView>(R.id.ciudadAutoCompleteTextView)
        textoCiudad.setOnClickListener{
            textoCiudad.requestFocus()
        }

        //Ícono y texto países
        val paisIcono: ImageView = findViewById(R.id.paisImageView)
        paisIcono.setImageResource(R.drawable.worldmap)

        val paises = arrayOf(
            "Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Costa Rica", "Cuba", "Ecuador",
            "El Salvador", "Guatemala", "Honduras", "México", "Nicaragua", "Panamá", "Paraguay",
            "Perú", "Puerto Rico", "República Dominicana", "Uruguay" ,"Venezuela")

        val textoPais = findViewById<AutoCompleteTextView>(R.id.paisAutoCompleteTextView)
        textoPais.setOnClickListener{
            textoPais.requestFocus()
        }

        //Ícono y texto ubicación
        val ubicacionIcono: ImageView = findViewById(R.id.ubicacionImageView)
        ubicacionIcono.setImageResource(R.drawable.map)

        val textoDireccion = findViewById<EditText>(R.id.direccionEditText)
        textoDireccion.setOnClickListener{
            textoDireccion.requestFocus()
        }





        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, paises)
        textoPais.threshold = 0
        textoPais.setAdapter(adapter)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ciudades)
        textoCiudad.threshold = 0
        textoCiudad.setAdapter(adapter)

        val botonSiguiente = findViewById<Button>(R.id.siguienteButton)

        //Validaciones
        botonSiguiente.setOnClickListener{
            if(textoTelefono.text.isNullOrEmpty()){
                Toast.makeText(this@ContactDataActivity, R.string.mensajeTelefono, Toast.LENGTH_LONG).show()
            }else if(textoCorreo.text.isNullOrEmpty()){
                Toast.makeText(this@ContactDataActivity, R.string.mensajeCorreo, Toast.LENGTH_LONG).show()
            }else if(!isEmail(textoCorreo.text.toString())){
                Toast.makeText(this@ContactDataActivity, R.string.mensajeCorreoInvalid, Toast.LENGTH_LONG).show()
            }else if(textoPais.text.isNullOrEmpty()){
                Toast.makeText(this@ContactDataActivity, R.string.mensajePais, Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@ContactDataActivity, R.string.mensajeAprobado, Toast.LENGTH_LONG).show()
                Log.i("Información personal",personalData.toString())
                Log.i("Información de contacto", "${getString(R.string.informacionDeContacto)}: \n ${getString(R.string.numeroDeTelefono)}: ${textoTelefono.text} \n " +
                        "${getString(R.string.direccion)}: ${textoDireccion.text} \n " +
                        "${getString(R.string.correo)}: ${textoCorreo.text} \n ${getString(R.string.pais)}: ${textoPais.text} \n" +
                        "${getString(R.string.ciudad)}: ${textoCiudad.text} \n")
            }
        }

    }

    private fun isEmail(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}
