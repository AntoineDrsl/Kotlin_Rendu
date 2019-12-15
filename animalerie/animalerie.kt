import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

fun main() {

    var animalerie = Animalerie()
    var choice: String = ""
    println("Bienvenue dans votre nouvelle animalerie, vous ferez un super gérant j'en suis sûr!")
    println("Entrez:\n 'animal-create' pour créer un nouvel animal\n 'animal-seeAll' pour voir tous vos animaux\n 'animal-see' pour voir un animal\n " +
            "'animal-listenAll' pour entendre tous vos animaux\n 'animal-listen' pour entendre un animal\n 'stop' pour arrêter le programme")

    while(choice != "stop") {
        choice = readLine()!!
        when (choice) {
            "animal-create" -> newAnimal(animalerie)
            "animal-seeAll" -> seeAll(animalerie)
            "animal-see" -> see(animalerie)
            "animal-listenAll" -> listenAll(animalerie)
            "animal-listen" -> listen(animalerie)
            "help" -> println("Entrez:\n 'animal-create' pour créer un nouvel animal\n 'animal-seeAll' pour voir tous vos animaux\n 'animal-see' pour voir un animal\n " +
                    "'animal-listenAll' pour entendre tous vos animaux\n 'animal-listen' pour entendre un animal\n 'stop' pour arrêter le programme")
            "stop" -> println("Merci d'avoir utilisé notre programme!")
            else -> println("Commande non reconnue, entrez 'help' pour voir toutes les commandes")
        }
    }

}

//INTERFACE
interface Animaux{

    fun parler(espece: String){
        when(espece) {
            "chien" -> println("Wouaf wouaf!")
            "chat" -> println("Miaou")
            "poisson" -> println("Bloup Bloup")
            "oiseau" -> println("Cuicui")
            "vache" -> println("Meuuuuu")
            "mouton" -> println("Bêêêêêh")
            "renard" -> println("What does the fox says?")
            "pokemon" -> println("Mon nom, mon nom")
            else -> println("Vous entendez des bruits de $espece")
        }
    }

    fun quiSuisJe(nom: String, espece: String, age: Int){
        println("Je m'appelle $nom, je suis un $espece et j'ai $age ans")
    }

}

//CLASS
class Animalerie() {

    private var animaux = arrayListOf<Animal>()

    private class Animal(var nom: String, var espece: String, var age: Int): Animaux

    fun addAnimal(nom: String, espece: String, age: Int) {
        var newAnimal = Animal(nom, espece, age)
        animaux.add(newAnimal)
    }

    fun seeAllAnimals() {
        for(animal in animaux) {
            animal.quiSuisJe(animal.nom, animal.espece, animal.age)
        }
    }

    fun seeAnimal(researchName: String) {
        var researchAnimal: Animal? = null
        for(animal in animaux) {
            if(researchAnimal === null) {
                researchAnimal = animaux.find { animal.nom == researchName }
                researchAnimal?.quiSuisJe(animal.nom, animal.espece, animal.age)
            }
        }
        if(researchAnimal === null) {
            println("Personne ne vous répond...")
        }
    }

    fun cacophonie() {
        for(animal in animaux) {
            animal.parler(animal.espece)
        }
    }

    fun listenAnimal(researchName: String) {
        var researchAnimal: Animal? = null
        for(animal in animaux) {
            if(researchAnimal === null) {
                researchAnimal = animaux.find { animal.nom == researchName }
                researchAnimal?.parler(animal.espece)
            }
        }
        if(researchAnimal === null) {
            println("Personne ne vous répond...")
        }
    }

}

//FUNCTIONS
fun newAnimal(animalerie: Animalerie){
    print("Entrez un nom: ")
    val newName: String = readLine()!!.capitalize()
    print("Entrez une espèce: ")
    val newEspece: String = readLine()!!.toLowerCase()
    print("Entrez un âge: ")
    val newAge: Int = setAge()
    animalerie.addAnimal(newName, newEspece, newAge)
    animalerie.seeAnimal(newName)
}

fun setAge(): Int {
    return try {
        readLine()!!.toInt()
    } catch (NumberException: Throwable) {
        print("L'âge doit être un nombre: ")
        setAge()
    }
}

fun seeAll(animalerie: Animalerie){
    animalerie.seeAllAnimals()
}

fun see(animalerie: Animalerie){
    print("Entrez le nom d'un de vos animaux: ")
    var seeName: String = readLine()!!.capitalize()
    animalerie.seeAnimal(seeName)
}

fun listenAll(animalerie: Animalerie){
    animalerie.cacophonie()
}

fun listen(animalerie: Animalerie){
    print("Entrez le nom d'un de vos animaux: ")
    var listenName: String = readLine()!!.capitalize()
    animalerie.listenAnimal(listenName)
}