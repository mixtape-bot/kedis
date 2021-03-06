package mixtape.oss.kedis.command.type

import mixtape.oss.kedis.command.RedisCommand

public class KeyExpiry private constructor(private val name: String, private val value: Long = -1) {

    public fun serialize(): RedisCommand.Option {
        val options = if (value == -1L) {
            emptyList<Any>()
        } else {
            listOf(value.toString())
        }

        return RedisCommand.Option(name, options)
    }

    public companion object {
        public fun seconds(seconds: Long): KeyExpiry = KeyExpiry("EX", seconds)
        public fun millis(millis: Long): KeyExpiry = KeyExpiry("PX", millis)
        public fun unixSeconds(unixSeconds: Long): KeyExpiry = KeyExpiry("EXAT", unixSeconds)
        public fun unixMillis(unixMillis: Long): KeyExpiry = KeyExpiry("PXAT", unixMillis)
        public fun keepTtl(): KeyExpiry = KeyExpiry("KEEPTTL")
    }
}
