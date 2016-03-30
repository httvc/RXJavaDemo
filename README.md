# RXJavaDemo
通过rx文档来将创建操作，变换操作，过滤操作等属性的注释以及代码的实现
创建操作有：
just( ) — 将一个或多个对象转换成发射这个或这些对象的一个Observable
from( ) — 将一个Iterable, 一个Future, 或者一个数组转换成一个Observable
repeat( ) — 创建一个重复发射指定数据或数据序列的Observable
repeatWhen( ) — 创建一个重复发射指定数据或数据序列的Observable，它依赖于另一个Observable发射的数据
create( ) — 使用一个函数从头创建一个Observable
defer( ) — 只有当订阅者订阅才创建Observable；为每个订阅创建一个新的Observable
range( ) — 创建一个发射指定范围的整数序列的Observable
interval( ) — 创建一个按照给定的时间间隔发射整数序列的Observable
timer( ) — 创建一个在给定的延时之后发射单个数据的Observable
empty( ) — 创建一个什么都不做直接通知完成的Observable
error( ) — 创建一个什么都不做直接通知错误的Observable
never( ) — 创建一个不发射任何数据的Observable

变换操作有：
这个页面展示了创建Observable的各种方法。

just( ) — 将一个或多个对象转换成发射这个或这些对象的一个Observable
from( ) — 将一个Iterable, 一个Future, 或者一个数组转换成一个Observable
repeat( ) — 创建一个重复发射指定数据或数据序列的Observable
repeatWhen( ) — 创建一个重复发射指定数据或数据序列的Observable，它依赖于另一个Observable发射的数据
create( ) — 使用一个函数从头创建一个Observable
defer( ) — 只有当订阅者订阅才创建Observable；为每个订阅创建一个新的Observable
range( ) — 创建一个发射指定范围的整数序列的Observable
interval( ) — 创建一个按照给定的时间间隔发射整数序列的Observable
timer( ) — 创建一个在给定的延时之后发射单个数据的Observable
empty( ) — 创建一个什么都不做直接通知完成的Observable
error( ) — 创建一个什么都不做直接通知错误的Observable
never( ) — 创建一个不发射任何数据的Observable

过滤操作有：
map( ) — 对序列的每一项都应用一个函数来变换Observable发射的数据序列
flatMap( ), concatMap( ), and flatMapIterable( ) — 将Observable发射的数据集合变换为Observables集合，然后将这些Observable发射的数据平坦化的放进一个单独的Observable
switchMap( ) — 将Observable发射的数据集合变换为Observables集合，然后只发射这些Observables最近发射的数据
scan( ) — 对Observable发射的每一项数据应用一个函数，然后按顺序依次发射每一个值
groupBy( ) — 将Observable分拆为Observable集合，将原始Observable发射的数据按Key分组，每一个Observable发射一组不同的数据
buffer( ) — 它定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个
window( ) — 定期将来自Observable的数据分拆成一些Observable窗口，然后发射这些窗口，而不是每次发射一项
cast( ) — 在发射之前强制将Observable发射的所有数据转换为指定类型

还有异步操作等的代码实现等等
