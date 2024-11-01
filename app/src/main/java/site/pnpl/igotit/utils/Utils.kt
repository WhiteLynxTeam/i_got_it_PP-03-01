package site.pnpl.igotit.utils

import site.pnpl.igotit.domain.models.HasUuid
import java.util.UUID


fun List<HasUuid>.randomUuid() {
    forEach { instance ->
        instance.uuid = UUID.randomUUID()
    }
}
