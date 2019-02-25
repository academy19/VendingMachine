import scala.annotation.tailrec

class VendingMachine(availableProducts : List[Product], stockLevel : Map[String, Int]) {

  val validCoins: List[Int] = List(1, 2, 5, 10, 20, 50, 100, 200).reverse

  def listAvailableProducts(): List[Product] = availableProducts

  private def findProduct(code : String): Option[Product]= {
    availableProducts.find(a => a.selectionCode == code)
  }

  def checkIfProductIsInStock (selectionCode : String):Boolean = {

    stockLevel.get(selectionCode) match {
      case None    => false
      case Some(0) => false
      case _       => true
    }
  }

  def getAProductIfInStock(selectionCode: String) : Either[String, Product] = {

    if (checkIfProductIsInStock(selectionCode)) {
      findProduct(selectionCode) match {
        case Some(product) => Right(product)
        case _ => Left("Product doesn't exist")
      }
    }
    else Left("Not in Stock")
  }

  private def ifCoinsInvalid (userCoins: List[Int]): Boolean = {

    !userCoins.forall(coin => validCoins.contains(coin))
  }

  def payAndGiveChange (userCoins: List[Int], priceOfProduct: Int): Either[String, List[Int]] = {

    val totalUserCoins = userCoins.sum

    if (ifCoinsInvalid(userCoins)) Left("Invalid coin(s)")

    else if (totalUserCoins == priceOfProduct) Right(Nil)

    else if (totalUserCoins < priceOfProduct)
      Left(s"Insufficient funds. Please insert ${priceOfProduct - totalUserCoins}p more.")

    else Right(calculateOptimumChange(totalUserCoins - priceOfProduct))

  }

  private def calculateOptimumChange(changeDue : Int, currentChange : List[Int] = Nil) : List[Int] = {

    if (changeDue > 0) {

      val firstChangeCoin = validCoins.filter(_ <= changeDue).head
      val newChangeDue = changeDue - firstChangeCoin

      calculateOptimumChange(newChangeDue, firstChangeCoin :: currentChange)
    }
    else currentChange
  }
}
