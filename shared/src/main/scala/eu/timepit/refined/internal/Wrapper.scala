package eu.timepit.refined
package internal

import shapeless.tag.@@

import scala.reflect.macros.Context

/**
 * Type class for wrapping a value of type `T` into `F` together with a
 * phantom type `P`.
 */
trait Wrapper[F[_, _]] {

  def wrap[T, P](t: T): F[T, P]

  def wrapM[T: c.WeakTypeTag, P: c.WeakTypeTag](c: Context)(t: c.Expr[T]): c.Expr[F[T, P]]

  def rewrapM[T: c.WeakTypeTag, A: c.WeakTypeTag, B: c.WeakTypeTag](c: Context)(ta: c.Expr[F[T, A]]): c.Expr[F[T, B]]
}

object Wrapper {

  def apply[F[_, _]](implicit w: Wrapper[F]): Wrapper[F] = w

  implicit def refinedWrapper: Wrapper[Refined] =
    new Wrapper[Refined] {
      override def wrap[T, P](t: T): Refined[T, P] =
        Refined(t)

      override def wrapM[T: c.WeakTypeTag, P: c.WeakTypeTag](c: Context)(t: c.Expr[T]): c.Expr[Refined[T, P]] =
        c.universe.reify(Refined[T, P](t.splice))

      override def rewrapM[T: c.WeakTypeTag, A: c.WeakTypeTag, B: c.WeakTypeTag](c: Context)(ta: c.Expr[Refined[T, A]]): c.Expr[Refined[T, B]] =
        c.universe.reify(ta.splice.asInstanceOf[Refined[T, B]])
    }

  implicit def tagWrapper: Wrapper[@@] =
    new Wrapper[@@] {
      override def wrap[T, P](t: T): T @@ P =
        t.asInstanceOf[T @@ P]

      override def wrapM[T: c.WeakTypeTag, P: c.WeakTypeTag](c: Context)(t: c.Expr[T]): c.Expr[T @@ P] =
        c.universe.reify(t.splice.asInstanceOf[T @@ P])

      override def rewrapM[T: c.WeakTypeTag, A: c.WeakTypeTag, B: c.WeakTypeTag](c: Context)(ta: c.Expr[T @@ A]): c.Expr[T @@ B] =
        c.universe.reify(ta.splice.asInstanceOf[T @@ B])
    }
}
