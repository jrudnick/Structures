package structures
package exercise

import simulacrum.{ noop, op, typeclass }

@typeclass trait FlatMapExercise[F[_]] extends Any with ApplyExercise[F] {

  @op(">>=", alias = true)
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]

  def flatten[A, B](ffa: F[F[A]]): F[A] = ??? // Hint: can we use other methods?

  @noop override def apply[A, B](fa: F[A])(f: F[A => B]): F[B] = ??? // Hint: can we use other methods?
}
