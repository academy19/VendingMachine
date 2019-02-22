class VendingMachine(availableProducts : List[Product], stockLevel : Map[String, Int]) {
  def getPrice(selectionCode: String) : Option[Int] = {
    ???
  }

  def getProducts(): List[Product] = availableProducts
}
