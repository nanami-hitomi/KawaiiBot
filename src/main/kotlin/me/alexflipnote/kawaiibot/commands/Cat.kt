package me.alexflipnote.kawaiibot.commands

import me.alexflipnote.kawaiibot.extensions.json
import me.alexflipnote.kawaiibot.extensions.thenException
import me.alexflipnote.kawaiibot.utils.RequestUtil
import me.aurieh.ichigo.core.CommandContext
import me.aurieh.ichigo.core.ICommand
import me.aurieh.ichigo.core.annotations.Command

@Command(description = "cats. Cats. CATS!")
class Cat : ICommand {
    override fun run(ctx: CommandContext) {
        RequestUtil.get("https://api.nekos.dev/api/v3/images/sfw/img/cat").thenAccept {
            val res = it.json()?.getJSONObject("data")?.getJSONObject("response")?.getString("url") ?: ""
            ctx.send(res ?: return@thenAccept ctx.send("Bad response ;-;"))
        }.thenException { ctx.send("I-I couldn't find any cats... I'm sorry ;-;") }
    }
}
