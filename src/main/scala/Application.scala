import scala.io.StdIn.readLine

object Application {

  val products =  List (
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

  val initialInventory = products.map(product => (product.selectionCode, 6)).toMap

  def main(args : Array[String]) : Unit = {
    val vm = new VendingMachine(products, initialInventory)

    vm.getProducts()
      .foreach(product => println(s"${product.name} ${product.price}p ${product.selectionCode}")
    )

    val userSelectionCode = readLine("Enter desired selection code")
    val price = vm.getPrice(userSelectionCode)

    println(s"$price")
  }
}