(ns user
  (:require [mount.core :as mount]
            [bug.figwheel :refer [start-fw stop-fw cljs]]
            bug.core))

(defn start []
  (mount/start-without #'bug.core/repl-server))

(defn stop []
  (mount/stop-except #'bug.core/repl-server))

(defn restart []
  (stop)
  (start))


