package structures
package exercise

import simulacrum.typeclass

@typeclass trait MonadExercise[F[_]] extends Any with FlatMapExercise[F] with ApplicativeExercise[F] {

  override def map[A, B](fa: F[A])(f: A => B): F[B] = ???
}
