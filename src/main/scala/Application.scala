import scala.io.StdIn
import scala.io.StdIn.readLine
object Application {

  def main(args : Array[String]) : Unit = {

    val productsList =  List (
        Confectionary(65, "A1", "Mars"),
        Confectionary(65, "A2", "Twirl"),
        Confectionary(65, "A3", "Wispa"),
        Confectionary(65, "A4", "Daim"),
        Confectionary(65, "A5", "Kinder Bueno"),
        Confectionary(65, "B1", "Snickers"),
        Crisps(80, "B2", "Prawn Cocktail"),
        Crisps(80, "B3", "Salt & Shake"),
        Crisps(80, "B4", "Skips"),
        Crisps(80, "B5", "Cool Doritos"),
        Crisps(80, "B6", "Wotsits")
    )

    val initialInventory = productsList.map(product => (product.selectionCode, 6)).toMap

    val theVendingMachine = new VendingMachine(productsList, initialInventory)

    println("Welcome. Please choose a product selection code. The available products are:")

    theVendingMachine.listAvailableProducts()
      .foreach(product => println
        (s"${product.name}: Code ${product.selectionCode} - ${product.price}p"))

    val userSelectionCode = readLine("Now, please enter a selection code\n")

    val productUserHasChosen = theVendingMachine.getAProductIfInStock(userSelectionCode)

    productUserHasChosen match {
      case Right(product) => takePayment(theVendingMachine, product)
      case _              => println("Not available")
    }
  }

  private def takePayment(theVendingMachine: VendingMachine, product: Product) = {
    println(s"You have selected ${product.name} which costs ${product.price}p")

    val insertedCoins = StdIn.readLine("Please insert coins.")
      .split(", ")
      .toList
      .map(_.toInt)

    println(theVendingMachine.payAndGiveChange(insertedCoins, product.price))

  }
}
