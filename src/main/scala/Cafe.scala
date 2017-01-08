sealed trait Item {
  val price: Double
}
sealed trait Drink extends Item
sealed trait Food extends Item
sealed trait Hot

case class Cola(price: Double = 0.5) extends Drink
case class Coffee(price: Double = 1) extends Drink with Hot
case class CheeseSandwich(price: Double = 2) extends Food
case class SteakSandwich(price: Double = 4.5) extends Food with Hot


object Cafe {

  val MAX_SERVICE_CHARGE = 20
  val FOOD_SERVICE_CHARGE = 1.1
  val HOT_FOOD_SERVICE_CHARGE = 1.2

  def bill(items: Seq[Item]) = items.foldLeft(0.0)(_ + _.price)

  def billWithCharges(items: Seq[Item]) = {
    val onlyDrinks = items.forall {
      case drink: Drink => true
      case _ => false
    }
    val hasHotFood = items exists {
      case drink: Food with Hot => true
      case _ => false
    }
    val hasFood = items exists {
      case drink: Food => true
      case _ => false
    }

    val totalBill = bill(items)

    (onlyDrinks, hasHotFood, hasFood) match {
      case (true, _, _) => totalBill
      case (_, true, _) =>
        if (totalBill * 0.2 < 20)
          roundBill(totalBill * HOT_FOOD_SERVICE_CHARGE)
        else
          roundBill(totalBill + MAX_SERVICE_CHARGE)
      case (_, _, true) => roundBill(totalBill * FOOD_SERVICE_CHARGE)
    }
  }

  def roundBill(total: Double) = {
    BigDecimal(total).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }
}