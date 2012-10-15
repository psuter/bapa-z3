package bapa

object Main {
  def main(args : Array[String]) : Unit = {
    // A very quick intro to using the plugin...
    
    import z3.scala._

    val ctx  = new Z3Context()
    val bapa = new BAPATheory(ctx)

    val a = ctx.mkFreshConst("A", bapa.mkSetSort)
    val b = ctx.mkFreshConst("B", bapa.mkSetSort)
  
    // A != B
    ctx.assertCnstr(ctx.mkDistinct(a, b))
    // A subset of B
    ctx.assertCnstr(bapa.mkSubsetEq(a, b)) 

    println(ctx.check) // Some(true), meaning SAT

    // | A U B | > | B | 
    val cardOfAUnionB = bapa.mkCard(bapa.mkUnion(a, b))
    ctx.assertCnstr(ctx.mkGT(cardOfAUnionB, bapa.mkCard(b)))

    println(ctx.check) // Some(false), meaning UNSAT
  }
}
