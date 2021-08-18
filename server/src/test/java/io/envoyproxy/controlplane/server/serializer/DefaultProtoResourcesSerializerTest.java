package io.envoyproxy.controlplane.server.serializer;

import static io.envoyproxy.controlplane.cache.Resources.ApiVersion.V2;
import static io.envoyproxy.controlplane.cache.Resources.ApiVersion.V3;
import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.Lists;
import com.google.protobuf.Any;
import io.envoyproxy.envoy.api.v2.ClusterLoadAssignment;
import java.util.Collection;
import java.util.List;
import org.junit.Test;

public class DefaultProtoResourcesSerializerTest {

  DefaultProtoResourcesSerializer serializer = new DefaultProtoResourcesSerializer();

  @Test
  public void shouldReturnDifferentInstanceOfSerializedProtoWhenV2ResourcesAreTheSame() {
    ClusterLoadAssignment endpoint = ClusterLoadAssignment.newBuilder()
        .setClusterName("service1")
        .build();

    Any serializedEndpoint = serializer.serialize(endpoint, V2);
    Any serializedSameEndpoint = serializer.serialize(endpoint, V2);
    Any v3SerializedEndpoint = serializer.serialize(endpoint, V3);
    Any v3SerializedSameEndpoint = serializer.serialize(endpoint, V3);

    assertThat(serializedEndpoint).isEqualTo(serializedSameEndpoint);
    assertThat(serializedEndpoint).isNotSameAs(serializedSameEndpoint);
    assertThat(v3SerializedEndpoint).isEqualTo(v3SerializedSameEndpoint);
    assertThat(v3SerializedEndpoint).isNotSameAs(v3SerializedSameEndpoint);
  }

  @Test
  public void shouldReturnDifferentInstancesOfSerializedProtoWhenV2ResourcesAreTheSame() {
    List<ClusterLoadAssignment> endpoints = Lists.newArrayList(
        ClusterLoadAssignment.newBuilder()
            .setClusterName("service1")
            .build(),
        ClusterLoadAssignment.newBuilder()
            .setClusterName("service2")
            .build()
    );

    Collection<Any> serializedEndpoints = serializer.serialize(endpoints, V2);
    Collection<Any> serializedSameEndpoints = serializer.serialize(endpoints, V2);
    Collection<Any> v3SerializedEndpoints = serializer.serialize(endpoints, V3);
    Collection<Any> v3SerializedSameEndpoints = serializer.serialize(endpoints, V3);

    assertThat(serializedEndpoints).isEqualTo(serializedSameEndpoints);
    assertThat(serializedEndpoints).isNotSameAs(serializedSameEndpoints);
    assertThat(serializedEndpoints) // elements are not the same instances
        .usingElementComparator((x, y) -> x == y ? 0 : 1)
        .doesNotContainAnyElementsOf(serializedSameEndpoints);

    assertThat(v3SerializedEndpoints).isEqualTo(v3SerializedSameEndpoints);
    assertThat(v3SerializedEndpoints).isNotSameAs(v3SerializedSameEndpoints);
    assertThat(v3SerializedEndpoints) // elements are not the same instances
        .usingElementComparator((x, y) -> x == y ? 0 : 1)
        .doesNotContainAnyElementsOf(v3SerializedSameEndpoints);
  }

  @Test
  public void shouldReturnDifferentInstanceOfSerializedProtoWhenV3ResourcesAreTheSame() {
    io.envoyproxy.envoy.config.endpoint.v3.ClusterLoadAssignment endpoint =
        io.envoyproxy.envoy.config.endpoint.v3.ClusterLoadAssignment
            .newBuilder()
            .setClusterName("service1")
            .build();

    Any serializedEndpoint = serializer.serialize(endpoint, V2);
    Any serializedSameEndpoint = serializer.serialize(endpoint, V2);
    Any v3SerializedEndpoint = serializer.serialize(endpoint, V3);
    Any v3SerializedSameEndpoint = serializer.serialize(endpoint, V3);

    assertThat(serializedEndpoint).isEqualTo(serializedSameEndpoint);
    assertThat(serializedEndpoint).isNotSameAs(serializedSameEndpoint);
    assertThat(v3SerializedEndpoint).isEqualTo(v3SerializedSameEndpoint);
    assertThat(v3SerializedEndpoint).isNotSameAs(v3SerializedSameEndpoint);
  }

  @Test
  public void shouldReturnDifferentInstancesOfSerializedProtoWhenV3ResourcesAreTheSame() {
    List<io.envoyproxy.envoy.config.endpoint.v3.ClusterLoadAssignment> endpoints = Lists.newArrayList(
        io.envoyproxy.envoy.config.endpoint.v3.ClusterLoadAssignment.newBuilder()
            .setClusterName("service1")
            .build(),
        io.envoyproxy.envoy.config.endpoint.v3.ClusterLoadAssignment.newBuilder()
            .setClusterName("service2")
            .build()
    );

    Collection<Any> serializedEndpoints = serializer.serialize(endpoints, V2);
    Collection<Any> serializedSameEndpoints = serializer.serialize(endpoints, V2);
    Collection<Any> v3SerializedEndpoints = serializer.serialize(endpoints, V3);
    Collection<Any> v3SerializedSameEndpoints = serializer.serialize(endpoints, V3);

    assertThat(serializedEndpoints).isEqualTo(serializedSameEndpoints);
    assertThat(serializedEndpoints).isNotSameAs(serializedSameEndpoints);
    assertThat(serializedEndpoints) // elements are not the same instances
        .usingElementComparator((x, y) -> x == y ? 0 : 1)
        .doesNotContainAnyElementsOf(serializedSameEndpoints);

    assertThat(v3SerializedEndpoints).isEqualTo(v3SerializedSameEndpoints);
    assertThat(v3SerializedEndpoints).isNotSameAs(v3SerializedSameEndpoints);
    assertThat(v3SerializedEndpoints) // elements are not the same instances
        .usingElementComparator((x, y) -> x == y ? 0 : 1)
        .doesNotContainAnyElementsOf(v3SerializedSameEndpoints);
  }
}
