(defproject http-kit-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [http-kit "2.1.18"]
                 [compojure "1.4.0"]]
  :main ^:skip-aot http-kit-demo.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
