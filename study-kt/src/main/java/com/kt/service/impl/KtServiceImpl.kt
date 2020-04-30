package com.kt.service.impl

import com.kt.model.RiderUser
import com.kt.service.KtService
import org.springframework.stereotype.Service

@Service
class KtServiceImpl : KtService {

    override
    fun foo(): RiderUser {
        val lists: ArrayList<RiderUser> = ArrayList()
        var riderUser = RiderUser(Long.MAX_VALUE, Double.MAX_VALUE)
        riderUser.id = 3
        lists.add(RiderUser(Long.MAX_VALUE, Double.MAX_VALUE))
        lists.add(RiderUser(Long.MAX_VALUE, Double.MAX_VALUE))
        lists.add(RiderUser(Long.MAX_VALUE, Double.MAX_VALUE))
        val posi = lists.filter { it.id > 0}
        return RiderUser(Long.MAX_VALUE, Double.MAX_VALUE)
    }
}

fun main() {
    foo(3,"dd")
}

fun index(): RiderUser {
    return RiderUser(Long.MAX_VALUE, Double.MAX_VALUE)
}

fun foo(a: Int, b: String){
    var c = a
    c = 3 + a
    print(c)
//    when {
//        else -> print("dd")
//    }
    var map = HashMap<String, Any>()
    map["1"] = "2"
    map["d"] = 321
    for ((k,v) in map){
        print("k: $k, v: $v")
    }
    print(map["d"])
    println("$a + $b")
}