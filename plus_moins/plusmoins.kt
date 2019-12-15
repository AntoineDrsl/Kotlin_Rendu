import kotlin.math.absoluteValue
import kotlin.system.exitProcess

fun main() {

    var playerNumber: Int = -1
    var counter: Int = 0
    val secretNumber: Int = chooseMode()

    while(playerNumber != secretNumber){
        counter++;
        print("Entrez un nombre (-1 pour abandonner): ")
        playerNumber = setNumber()

        when{
            playerNumber == -1 -> youLoose(secretNumber)
            playerNumber > secretNumber -> println("C'est moins!")
            playerNumber < secretNumber -> println("C'est plus!")
            playerNumber == secretNumber -> youWin(counter)
        }

        when(playerNumber){
            in secretNumber..(secretNumber + 10) -> println("Vous êtes brûlant!")
            in (secretNumber - 10)..secretNumber -> println("Vous êtes brûlant!")
            in secretNumber..(secretNumber + 50) -> println("Vous êtes chaud!")
            in (secretNumber - 50)..secretNumber -> println("Vous êtes chaud!")
            in secretNumber..(secretNumber + 100) -> println("Vous êtes tiède!")
            in (secretNumber - 100)..secretNumber -> println("Vous êtes tiède!")
        }
    }

}

fun chooseMode(): Int {
    var secretNumber = 0
    while(secretNumber == 0) {
        println("Choisissez votre mode de jeu: \nEntrez 'solo' pour jouer tout seul \nEntrez 'multi' pour jouer à 2")
        secretNumber = when (readLine()) {
            "solo" -> (1..1000).shuffled().first();
            "multi" -> setSecretNumber();
            else -> 0
        }
    }
    return secretNumber
}

fun setSecretNumber(): Int {
    return try {
        print("Entrez un nombre secret: ");
        readLine()!!.toInt().absoluteValue
    } catch (NumberException: Throwable) {
        setSecretNumber()
    }
}

fun setNumber(): Int {
    return try {
        readLine()!!.toInt()
    } catch (NumberException: Throwable) {
        print("Veuillez entrer un nombre (-1 pour abandonner): ")
        setNumber()
    }
}

fun youWin(counter: Int) {
    println("C'est gagné en $counter coups!")
    exitProcess(-1)
}

fun youLoose(secretNumber: Int) {
    println("Dommage! Le nombre était $secretNumber :(")
    exitProcess(-1)
}

