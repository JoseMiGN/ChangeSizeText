# ChangeSizeText

### Contenido
- [Clases POJO](#Clases)
- [Clase Application personalizada](#Application)
- [Activities](#Activities)
- [Posibles errores](#Posibles-errores)

### Proyecto finalizado
<p align="center">
<img src="https://user-images.githubusercontent.com/36245471/136454994-35d01f7c-5fb5-49bd-a104-8e7a8bf6d735.png" width="35%" /> <img src="https://user-images.githubusercontent.com/36245471/136455179-ef2f00c9-3cd3-4b26-8358-247db539feef.png" width=35% />
</p>

### Objetivo del proyecto
El objetivo del proyecto es pasar la información de un EditText de una activity a otra activity además de cambiar el tamaño del texto mediante un SeekBar.


#### Añadir vieBinding
En el build.gradle (modulo) añadir el siguiente codigo.

```Java
viewBinding {
        enabled = true
    }
```

#### Layout
La activity_change_size_text.xml debe tener:
 - 1 EditText.
 - 1 SeekBar.
 - 1 Buttón.

La activity_view_message.xml debe tener:
 - 1 TextView.

### Clases
----

Las dos clases que vamos a crear deberan implementar la interfaz Serializable porque se pasarán en un Intent.

#### Clase User
Es la clase que guarda la información del login del usuario para ello necesitamos guardar el nombre y el email.

#### Clase Message
Es la clase que vamos a enviar y guardar desde el bundle. Guarda la información de un mensaje, un usuario y el tamaño a mostrar el mensaje.

<table>
<tr>
<th> User </th>
<th> Message </th>
</tr>
<tr>
<td>

```Java
public class User implements Serializable{
    private String name;
    private String email;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
```

</td>
<td>

```Java
public class Message implements Serializable {
    private User user;
    private String message;
    private int size;

    public Message() {
    }

    public Message(User user, String message, int size) {
        this.user = user;
        this.message = message;
        this.size = size;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Message{" +
                "user=" + user +
                ", message='" + message + '\'' +
                ", size=" + size +
                '}';
    }
}
```

</td>
</tr>
</table>

### Application
Crear una clase llamada ChangeSizeApplication que extienda de _Application_.
Añadir un usuario de forma estática para simular el login en el _onCreate_.
Añadir un método _getUser()_ para poder obtener desde el _getApplication()_

``` Java
    public class ChangeSizeApplication extends Application {
        private User user;
        @Override
        public void onCreate() {
            super.onCreate();
            user = new User("josemi","josemiguelgodoynavarrete.dam@gmail.com");
        }

        public User getUser() {
            return user;
        }
    }
```

### Activities
----

#### ChangeSizeTextActivity
1. Hacer el binding para ello creamos un objeto de la clase con el mismo nombre que el xml más Binding al final.

```Java
ActivityChangeSizeTextBinding binding;
```

2. Inflar el layout y vincular la vista con el binding creado.

```Java
@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityChangeSizeTextBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    }
```

3. Crear el onClickListener del botón (nombre del botón btSend).

```Java
binding.btSend.setOnClickListener(v -> {
            //1. Crear objeto Bundle
            Bundle bundle = new Bundle();
            //2. Añadir el objeto mensaje al Bundle (contiene el usuario[nombre y correo], un mensaje y un entero)
            Message message = new Message(
                    ((ChangeSizeApplication) getApplication()).getUser(), // Casting de Application a ChangeSizeApplication, crear metodo getUser en la clase ChangeSizeApplication
                    binding.edMessage.getText().toString(),
                    binding.skSize.getProgress()
            );
            bundle.putSerializable("message", message); // Implementar la clase Serializable
            //3. Enviar Intent con el Bundle
            Intent intent = new Intent(this, ViewMessageActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
```
#### ViewMessageActivity
1. Hacer el binding, de la misma forma que en la activity anterior, creamos un objeto de la clase con el mismo nombre que el xml más Binding al final.

```Java
ActivityViewMessageBinding binding;
```

2. Inflar el layout y vincular la vista con el binding creado.

```Java
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
}
```

3. Recoger el mensaje por medio del Intent y visualizarlo un mensaje que se ha inicializado en la clase ChangeSizeTextActivity.

``` Java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //1. Recoger intent
        Intent intent = getIntent();
        //2. Recoger el mensaje
        Message message = (Message) intent.getExtras().getSerializable("message");
        //3. Actualizar la vista
        binding.tvMessage.setText(message.getMessage());
        binding.tvMessage.setTextSize(message.getSize());
        /*
        Todas las Activity tienen acceso a la información de la clase
        Application desde el método getApplication. Se debe realizar un casting
        explicito.
         */
        Log.d(TAG, ((ChangeSizeApplication)getApplication()).getUser().toString());
    }
```

### Posibles errores

1. En este ejercicio se ha creado una clase personalizada de Application, para que el ejercicio funcione se debe añadir el atributo _android:name_ con el nombre de la clase creada.
    <code>android:name=".ChangeSizeApplication"</code>
ERROR:

```
java.lang.ClassCastException: android.app.Application cannot be cast to com.example.changesizetext.ChangeSizeApplication
```


2. La clase _User_ debe implementar la interfaz Serializable porque esta contenida dentro de la clase Mensaje que se pasa dentro de un Intent.
ERROR en <code> ((ChangeSizeApplication) getApplication()).getUser() </code>:

```
    java.lang.RuntimeException: Parcelable encountered IOException writing serializable object (name = com.example.changesizetext.data.model.Message)
```



