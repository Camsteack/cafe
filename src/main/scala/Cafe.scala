sealed trait Item {
  val price: Double
}

case class Cola(price: Double = 0.5)
case class Coffee(price: Double = 1) extends Item
case class CheeseSandwich(price: Double = 2) extends Item
case class SteakSandwich(price: Double = 4.5) extends Item

object Cafe {

  def bill(items: Seq[Item]) = items.foldLeft(0.0)(_ + _.price)

}