---
layout: docs
title: Search API
permalink: search
---

# Search API

Github4s supports the [Search API](https://docs.github.com/rest/search). As a result,
with Github4s, you can interact with:

- [Search code](#search-code)

The following examples assume the following code:

```scala mdoc:silent

import cats.effect.IO
import github4s.Github
import org.http4s.client.{Client, JavaNetClientBuilder}


val httpClient: Client[IO] = JavaNetClientBuilder[IO].create // You can use any http4s backend

val accessToken = sys.env.get("GITHUB_TOKEN")
val gh = Github[IO](httpClient, accessToken)
```

## Search code

You can search code with `searchCode`; it takes as arguments:

- `query`: query parameters (at least one must be provided).
- `searchParams`: search parameters (see ["Searching code"](https://docs.github.com/en/search-github/searching-on-github/searching-code))
- `textMatches`: enable text matches location (see ["Text match metadata"](https://docs.github.com/en/rest/search#text-match-metadata))
- `pagination`: Limit and Offset for pagination, optional.

To search `github` in the scala code within this project repository:

```scala mdoc:compile-only
val results = gh.search.searchCode(
  query = "github",
  searchParams = List(
    SearchCodeParam.Repository("47degrees", "github4s"),
    SearchCodeParam.Extension("scala")
  )
)
results.flatMap(_.result match {
  case Left(e)  => IO.println(s"Something went wrong: ${e.getMessage}")
  case Right(r) => IO.println(r)
})
```

The `result` on the right is the corresponding [SearchCodeResult][search-code-scala].

See [the API doc](https://docs.github.com/en/rest/search#search-code) for full reference.


[search-code-scala]: https://github.com/47degrees/github4s/blob/main/github4s/shared/src/main/scala/github4s/domain/SearchCode.scala
