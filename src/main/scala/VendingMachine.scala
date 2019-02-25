class VendingMachine(availableProducts : List[Product], stockLevel : Map[String, Int]) {

  def findProduct(code : String): Option[Product]={
    availableProducts.find(a => a.selectionCode == code)
  }

  def checkIfProductIsInStock (selectionCode : String):Boolean = {

    stockLevel.get(selectionCode) match {
      case None    => false
      case Some(0) => false
      case _       => true
    }
  }

  def getAProductIfInStock(selectionCode: String) : Either[Product,String] = {

    if (checkIfProductIsInStock(selectionCode)) {
      findProduct(selectionCode) match {
        case Some(x) => Left(x)
        case _ => Right("None Available")
      }
    }
    else Right("Not in Stock")
  }

  def listAvailableProducts(): List[Product] = availableProducts
}
