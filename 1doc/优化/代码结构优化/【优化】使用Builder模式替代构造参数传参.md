###使用Builder模式替代构造参数传参

	前言：关于传递参数,当参数过多的时候我们可以考虑使用建造者模式。
  #没用 Builder模式 之前是这样传参的：
  如下所示,构造方法里面的参数一大堆，看起来就非常的混乱。
    ParameterBuilder from(Parameter other) {
    return name(other.getName())
        .allowableValues(other.getAllowableValues())
        .allowMultiple(other.isAllowMultiple())
        .defaultValue(other.getDefaultValue())
        .description(other.getDescription())
        .modelRef(other.getModelRef())
        .parameterAccess(other.getParamAccess())
        .parameterType(other.getParamType())
        .required(other.isRequired())
        .type(other.getType().orNull())
        .hidden(other.isHidden())
        .vendorExtensions(other.getVendorExtentions());
  }

  	 总结：
	相比只通过一个构造器创建实例，JavaBean模式的实例的构造过程被分成了好几个过程。

	我们完全有可能在属性不完整的情况下使用这个实例。

	当然，Builder也有缺点。

	缺点1.创建实例前都要创建一个Builder实例。

	缺点2.Builder模式编写起来较为冗长。

	但是，当构建一个实例需要很多步骤(或者很多让人混淆的参数)的时候，Builder模式是个不错的选择。