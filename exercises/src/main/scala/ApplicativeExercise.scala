package structures
package exercise

import simulacrum.typeclass

/**
 * Type class that describes functors which have a lawful `Apply` instance and
 * that support a `pure` method which adheres to the laws described in
 * [[structures.laws.ApplicativeLaws]].
 *
 * Also known as idioms.
 *
 * @see http://strictlypositive.org/IdiomLite.pdf
 */
@typeclass trait ApplicativeExercise[F[_]] extends Any with ApplyExercise[F] { self =>

  def pure[A](a: A): F[A]

  override def map[A, B](fa: F[A])(f: A => B): F[B] = ???

  def compose[G[_]: Applicative]: Applicative[Lambda[X => F[G[X]]]] = ??? // Trickier
}

object Applicative {

  trait Composite[F[_], G[_]] extends Any with Applicative[Lambda[X => F[G[X]]]] with Apply.Composite[F, G] {
    def F: Applicative[F]
    def G: Applicative[G]
    def pure[A](a: A): F[G[A]] = F.pure(G.pure(a))
  }
}
