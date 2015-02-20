package structures
package exercise

import simulacrum.{ typeclass, noop }

/**
 * Type class that describes functors that support an `apply` method which adheres
 * to the laws described in [[structures.laws.ApplyLaws]].
 *
 * This type class models a more general version of an [[Applicative]] -- specifically, there's
 * no requirement for the `pure` method to exist.
 */
@typeclass trait ApplyExercise[F[_]] extends Any with Functor[F] { self =>

  def apply[A, B](fa: F[A])(f: F[A => B]): F[B]

  @noop def flip[A, B](f: F[A => B]): F[A] => F[B] = ???


  @noop def apply2[A, B, X](fa: F[A], fb: F[B])(f: F[(A, B) => X]): F[X] =
    ???

  @noop def apply3[A, B, C, X](fa: F[A], fb: F[B], fc: F[C])(f: F[(A, B, C) => X]): F[X] =
    ???



  @noop def map2[A, B, X](fa: F[A], fb: F[B])(f: (A, B) => X): F[X] = ???

  @noop def map3[A, B, C, X](fa: F[A], fb: F[B], fc: F[C])(f: (A, B, C) => X): F[X] = ???

  @noop def tuple2[A, B](fa: F[A], fb: F[B]): F[(A, B)] = ???

  def compose[G[_]: ApplyExercise]: ApplyExercise[Lambda[X => F[G[X]]]] =
    new ApplyExercise.Composite[F, G] {
      def F = self
      def G = ApplyExercise[G]
    }
}

object ApplyExercise {

  trait Composite[F[_], G[_]] extends Any with ApplyExercise[Lambda[X => F[G[X]]]] with Functor.Composite[F, G] {
    def F: ApplyExercise[F]
    def G: ApplyExercise[G]
    override def apply[A, B](fa: F[G[A]])(f: F[G[A => B]]): F[G[B]] = {
      val flipped: F[G[A] => G[B]] = F.map(f)(G.flip)
      F.apply(fa)(flipped)
    }
  }
}
